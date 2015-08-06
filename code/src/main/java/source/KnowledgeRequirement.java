package source;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Indexed;

import java.util.List;

/**
 * @author Hamid Ramazani (mrramazani@gmail.com)
 *         Created on 7/31/2015
 */
@Entity
public class KnowledgeRequirement {
    @Id
    private ObjectId id;
    private String name;
    private String text;
    private List<String> tags;

    public KnowledgeRequirement(String name, String text, List<String> tags) {
        this.name = name;
        this.text = text;
        this.tags = tags;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
}
