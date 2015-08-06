package user;


/**
 * @author Hamid Ramazani (mrramazani@gmail.com)
 *         Created on 7/25/2015
 */
public class Employee extends User {
    private double overallScore;


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
