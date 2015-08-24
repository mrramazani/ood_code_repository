package user;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

/**
 * Created by hamid on 8/24/2015.
 */
@Entity
public class EmployeeRaiseRate {
    @Id
    private ObjectId id;
    private int rate;
    private static EmployeeRaiseRate instance = new EmployeeRaiseRate();

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public static EmployeeRaiseRate getRate() {
        return instance;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    private EmployeeRaiseRate() {
    }
}
