package database;

import com.mongodb.MongoClient;
import content.Content;
import content.ContentChangeLog;
import content.ContentLogType;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.DatastoreImpl;
import org.mongodb.morphia.Key;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.dao.BasicDAO;
import org.mongodb.morphia.query.Query;
import source.Source;
import user.Role;

import java.util.List;

/**
 * @author Hamid Ramazani (mrramazani@gmail.com)
 *         Created on 7/25/2015
 */
public class ContentRepository extends BasicDAO<Content, String> {


    public ContentRepository(Morphia morphia, MongoClient mongo) {
        super(mongo, morphia, "test");
    }

    public Content search(String name) {
        Query<Content> query = createQuery().field("name").equal(name).field("obsolete").equal(false);
        return findOne(query);
    }

    public boolean isCopy(String path) {
        Query<Content> query = createQuery().field("files").equal(path);
        if (find(query).asList().size() > 0)
            return true;
        return false;
    }

    public List<Content> findForRole(Role role) {
        Query<Content> query = createQuery().field("accessRole").equal(role).limit(20);
        return find(query).asList();
    }

}

