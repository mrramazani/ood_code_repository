package database;

import com.mongodb.MongoClient;
import content.Content;
import content.ContentChangeLog;
import org.mongodb.morphia.Key;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.dao.BasicDAO;
import org.mongodb.morphia.query.Query;

import java.util.List;

/**
 * @author Hamid Ramazani (mrramazani@gmail.com)
 *         Created on 7/31/2015
 */
public class ContentChangeRepository extends BasicDAO<ContentChangeLog, String> {

    public ContentChangeRepository(Morphia morphia, MongoClient mongoClient, String dbName) {
        super(mongoClient, morphia, dbName);
    }

    public List<ContentChangeLog> getLogForContent(Content content) {
        Query<ContentChangeLog> query = createQuery().disableValidation().field("content").equal(new Key<Content>(Content.class, "Content", content.getId()));
        return find(query).asList();
    }
}
