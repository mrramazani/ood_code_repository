package repository;

import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import content.Comment;
import content.Content;
import org.mongodb.morphia.Key;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.dao.BasicDAO;
import org.mongodb.morphia.query.Query;

import java.util.List;

/**
 * @author Hamid Ramazani (mrramazani@gmail.com)
 *         Created on 7/23/2015
 */
public class CommentRepository extends BasicDAO<Comment, String>{
//    DBCollection commentCollection =

    public CommentRepository(Morphia morphia, MongoClient mongo) {
        super(mongo, morphia, "test");
    }

    public List<Comment> getComments(Content content) {
        Query<Comment> query = createQuery().disableValidation().field("content").equal(new Key<Content>(Content.class, "content", content.getId()));
        return find(query).asList();
    }
}
