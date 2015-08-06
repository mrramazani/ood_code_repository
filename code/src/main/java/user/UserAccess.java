package user;

import content.Content;
import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

/**
 * Created by hamid on 8/5/2015.
 */
@Entity
public class UserAccess {
    @Id
    private ObjectId id;
    private User user;
    private Content accessibleContent;

    public UserAccess(User user, Content accessibleContent) {
        this.user = user;
        this.accessibleContent = accessibleContent;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Content getAccessibleContent() {
        return accessibleContent;
    }

    public void setAccessibleContent(Content accessibleContent) {
        this.accessibleContent = accessibleContent;
    }
}
