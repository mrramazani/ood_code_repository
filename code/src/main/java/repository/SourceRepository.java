package repository;

import com.mongodb.MongoClient;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.dao.BasicDAO;
import org.mongodb.morphia.query.Query;
import source.Source;
import user.User;

import java.util.List;

/**
 * @author Hamid Ramazani (mrramazani@gmail.com)
 *         Created on 7/27/2015
 */
public class SourceRepository extends BasicDAO<Source, String> {

    public SourceRepository(MongoClient mongoClient, Morphia morphia, String dbName) {
        super(mongoClient, morphia, dbName);
    }

    public List<Source> search(String name) {
        Query<Source> query = createQuery().field("name").equal(name);
        return find(query).asList();
    }
}
