package database;

import com.mongodb.MongoClient;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.DatastoreImpl;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.dao.BasicDAO;
import org.mongodb.morphia.query.Query;
import org.mongodb.morphia.query.UpdateOperations;
import user.EmployeeRaiseRate;
import user.User;

import java.util.List;

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
        if (null != findOne(query))
            save(user);
    }

    public EmployeeRaiseRate getRaiseRate() {
        List<EmployeeRaiseRate> employeeRaiseRates = super.getDatastore().createQuery(EmployeeRaiseRate.class).asList();
        if (employeeRaiseRates.size() > 0)
            return employeeRaiseRates.get(0);
        else {
            super.getDatastore().save(EmployeeRaiseRate.getRate());
            return getRaiseRate();
        }
    }

    public void setRaiseRate(int rate) {
        List<EmployeeRaiseRate> employeeRaiseRates = super.getDatastore().createQuery(EmployeeRaiseRate.class).asList();
        if (employeeRaiseRates.size() > 0) {
            employeeRaiseRates.get(0).setRate(rate);
            UpdateOperations<EmployeeRaiseRate> updateOperations = super.getDatastore().createUpdateOperations(EmployeeRaiseRate.class).set("rate", rate);
            super.getDatastore().update(employeeRaiseRates.get(0), updateOperations);
        }
        else {
            super.getDatastore().save(EmployeeRaiseRate.getRate());
            setRaiseRate(rate);
        }
    }
}
