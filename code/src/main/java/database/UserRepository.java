package database;

import com.mongodb.MongoClient;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.dao.BasicDAO;
import org.mongodb.morphia.query.Query;
import user.User;

/**
 * @author Hamid Ramazani (mrramazani@gmail.com)
 *         Created on 7/25/2015
 */
public class UserRepository extends BasicDAO<User, String> {

    public UserRepository(Morphia morphia, MongoClient mongo) {
        super(mongo, morphia, "test");
    }



    public User findByUsername(String username) {
        Query<User> query = createQuery().field("username").equal(username);
        return findOne(query);
    }

    public boolean authenticate(String usr, String pwd) {
        Query<User> query = createQuery().field("username").equal(usr).field("password").equal(pwd);
        if (null != findOne(query)) {
            return true;
        }
        return false;
    }

    public void editUser(User user) {
        Query<User> query = createQuery().field("username").equal(user.getUsername());
//        update(query, )
        if (null != findOne(query))
            save(user);
    }
}
