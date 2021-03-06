package user;

import abstractCatalogue.AbstractCatalogue;
import database.UserActivityRepository;
import database.UserRepository;
import org.mongodb.morphia.query.Query;

import java.util.LinkedList;
import java.util.List;

/**
 * @author Hamid Ramazani (mrramazani@gmail.com)
 *         Created on 7/21/2015
 */
public class UserCatalogue extends AbstractCatalogue{

    private UserRepository userRepository;
    private UserActivityRepository activityRepository;


    public UserCatalogue() {
        super();
        userRepository = new UserRepository(super.getMorphia(), super.getMongoClient());
        activityRepository = new UserActivityRepository(super.getMongoClient(), super.getMorphia(), "test");
    }

    private List<User> users = new LinkedList<User>();

    // TODO
    public boolean login(User user) {
        if (authenticate(user.getUsername(), user.getPassword()) != null)
            return true;
        return false;
    }

    public boolean logout(User user) {
        return true;
    }

    public void addUser(User user) {
        User existingUser = userRepository.findByUsername(user.getUsername());
        if (null != existingUser)
            user.setId(existingUser.getId());
        userRepository.save(user);
    }

    public void editUser(User user) {
        userRepository.save(user);
    }

    public void deleteUser(User user) {
        userRepository.delete(user);
    }

    public List<User> list() {
        return users;
    }

    // TODO
    public List<User> search(String keyword) {
        Query<User> query = userRepository.createQuery().field("username").containsIgnoreCase(keyword);
        return userRepository.find(query).asList();
    }

    public List<UserActivityLog> getActivities(String username) {
        User user = userRepository.findByUsername(username);
        return activityRepository.getUserActivityForUser(user);
    }

    public List<UserActivityLog> getSpecializedLog(String username, ActivityType type) {
        final User userByUsername = getUserByUsername(username);
        return activityRepository.getUserActivityForUserByType(userByUsername, type);
    }

    public void addUserActivity(UserActivityLog log) {
        activityRepository.save(log);
    }

    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public User authenticate(String username, String password) {
        User byUsername = userRepository.findByUsername(username);
        if (byUsername.getPassword().equals(password))
            return byUsername;
        return null;
    }

    public void changeRaiseRate(int rate) {
        userRepository.setRaiseRate(rate);
    }
}
