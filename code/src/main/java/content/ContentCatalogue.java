package content;

import abstractCatalogue.AbstractCatalogue;
import com.mongodb.client.AggregateIterable;
import com.mongodb.client.MongoDatabase;
import database.CommentRepository;
import database.ContentChangeRepository;
import database.ContentRepository;
import database.RelationshipRepository;
import org.bson.Document;
import org.mongodb.morphia.Key;
import org.mongodb.morphia.query.Query;
import org.mongodb.morphia.query.QueryResults;
import source.Source;
import user.Role;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Hamid Ramazani (mrramazani@gmail.com)
 *         Created on 7/23/2015
 */
public class ContentCatalogue extends AbstractCatalogue {

    private ContentRepository contentRepository;
    private ContentChangeRepository contentChangeRepository;
    private CommentRepository commentRepository;
    private RelationshipRepository relationshipRepository;

    public ContentCatalogue() {
        super();
        contentRepository = new ContentRepository(super.getMorphia(), super.getMongoClient());
        contentChangeRepository = new ContentChangeRepository(super.getMorphia(), super.getMongoClient(), "test");
        commentRepository = new CommentRepository(super.getMorphia(), super.getMongoClient());
        relationshipRepository = new RelationshipRepository(super.getMorphia(), super.getMongoClient(), "test");
    }

    public List<Content> list() {
        return contentRepository.find().asList();
    }

    public List<Content> listForRole(Role role) {
        return contentRepository.findForRole(role);
    }

    public void addContent(Content content) {
        contentRepository.save(content);
    }

    public void editContent(Content content) {contentRepository.save(content);}

    public void log(ContentChangeLog log) {
        contentChangeRepository.save(log);
    }

    public void addRelation(Relationship relationship) {
        relationshipRepository.save(relationship);
    }

    public boolean isCopy(String path) {
        return contentRepository.isCopy(path);
    }

    public List<ContentChangeLog> getLog(String contentName) {
        Content search = contentRepository.search(contentName);
        return contentChangeRepository.getLogForContent(search);
    }

    public Content search(String name) {
        return contentRepository.search(name);
    }

    public List<Comment> getCommentsOfContent(String name) {
        Content search = contentRepository.search(name);

        return commentRepository.getComments(search);
    }

    public List<ContentChangeLog> getSpecificLog(Content content, ContentLogType type) {
        return contentChangeRepository.getSpecificLog(content, type);
    }

    public void addComment(Comment comment) {
        commentRepository.save(comment);
    }

    public Object[][] getSourceContentViews(Source src) {

        final Query<Content> query = contentRepository.createQuery().disableValidation().field("source").equal(new Key<Source>(Source.class, "Source", src.getId()));
        List<Content> contents = contentRepository.find(query).asList();
        Object[][] result = new Object[contents.size()][3];
        for (int i = 0; i < contents.size(); i++) {
            Query<ContentChangeLog> viewQuery = contentChangeRepository.createQuery().disableValidation().field("contentLogType").equal(ContentLogType.VIEW)
                    .field("content").equal(new Key<Content>(Content.class, "Content", contents.get(i).getId()));
            long viewCount = contentChangeRepository.count(viewQuery);
            result[i][0] = contents.get(i).getName();
            result[i][1] = contents.get(i).getDate();
            result[i][2] = viewCount;
        }
        return result;
    }


}
