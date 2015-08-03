package repository;

import com.mongodb.MongoClient;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.dao.BasicDAO;
import org.mongodb.morphia.query.Query;
import user.User;
import user.UserActivityLog;

import java.util.List;

/**
 * @author Hamid Ramazani (mrramazani@gmail.com)
 *         Created on 7/31/2015
 */
public class UserActivityRepository extends BasicDAO<UserActivityLog, String> {

    public UserActivityRepository(MongoClient mongoClient, Morphia morphia, String dbName) {
        super(mongoClient, morphia, dbName);
    }

    public List<UserActivityLog> getUserActivityForUser(User user) {
        Query<UserActivityLog> query = createQuery().field("user").equal(user);
        return this.find(query).asList();
    }
}
