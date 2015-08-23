package content;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Indexed;
import org.mongodb.morphia.annotations.Reference;
import source.Source;
import user.Role;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by hamid on 7/8/2015.
 */
public class Content {
    @Id
    private ObjectId id;
    @Indexed(unique = true)
    private String name;
    private String version;
    private Date date;
    private String text;
    @Reference
    private Source source;
    private List<String> tags = new ArrayList<String>();
    private String files;
    private List<Double> ratings = new ArrayList<Double>();
    private double averageRating;
    private boolean obsolete;
    private Role accessRole;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public String getFiles() {
        return files;
    }

    public void setFiles(String files) {
        this.files = files;
    }

    public List<Double> getRatings() {
        return ratings;
    }

    public void setRatings(List<Double> ratings) {
        this.ratings = ratings;
    }

    public double getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(double averageRating) {
        this.averageRating = averageRating;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public Source getSource() {
        return source;
    }

    public void setSource(Source source) {
        this.source = source;
    }

    public boolean isObsolete() {
        return obsolete;
    }

    public void setObsolete(boolean obsolete) {
        this.obsolete = obsolete;
    }

    public Role getAccessRole() {
        return accessRole;
    }

    public void setAccessRole(Role accessRole) {
        this.accessRole = accessRole;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}
