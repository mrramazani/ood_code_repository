package content;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Reference;
import user.User;

import java.util.Date;

/**
 * @author Hamid Ramazani (mrramazani@gmail.com)
 *         Created on 7/31/2015
 */
@Entity
public class ContentChangeLog {
    @Id
    private ObjectId id;
    @Reference
    private Content content;
    private ChangeType changeType;
    private Date date;
    private User user;

    public ContentChangeLog(Content content, ChangeType changeType, Date date, User user) {
        this.content = content;
        this.changeType = changeType;
        this.date = date;
        this.user = user;
    }

    public ChangeType getChangeType() {
        return changeType;
    }

    public void setChangeType(ChangeType changeType) {
        this.changeType = changeType;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Content getContent() {
        return content;
    }

    public void setContent(Content content) {
        this.content = content;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
