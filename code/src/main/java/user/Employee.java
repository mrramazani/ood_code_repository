package user;


/**
 * @author Hamid Ramazani (mrramazani@gmail.com)
 *         Created on 7/25/2015
 */
public class Employee extends User {
    private AccessLevel accessLevel;
    private double overallScore;
//    private boolean isBanned;

    public void raiseAccess() {
        if (this.accessLevel.equals(AccessLevel.ADMIN))
            this.accessLevel = AccessLevel.USER;
    }

    public void lowerAccess() {
        if (this.accessLevel.equals(AccessLevel.USER))
            this.accessLevel = AccessLevel.ADMIN;
    }

//    public void ban() {
//        isBanned = true;
//    }

    public void listScoreChanges() {

    }

    public Employee(User user) {
        super();
        setId(user.getId());
        setEmail(user.getEmail());
        setFirstName(user.getFirstName());
        setLastName(user.getLastName());
        setUsername(user.getUsername());
        setPassword(user.getPassword());
        setAdmin(user.isAdmin());
        setExpertise(user.getExpertise());
    }

    public Employee() {
    }

    public void changeScore(double score) {
        overallScore = score;
    }

    public AccessLevel getAccessLevel() {
        return accessLevel;
    }

    public void setAccessLevel(AccessLevel accessLevel) {
        this.accessLevel = accessLevel;
    }

    public double getOverallScore() {
        return overallScore;
    }

    public void setOverallScore(double overallScore) {
        this.overallScore = overallScore;
    }

//    public boolean isBanned() {
//        return isBanned;
//    }

}
