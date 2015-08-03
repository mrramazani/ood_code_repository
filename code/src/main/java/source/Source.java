package source;

import content.Content;
import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

import java.util.List;

/**
 * @author Hamid Ramazani (mrramazani@gmail.com)
 *         Created on 7/27/2015
 */
@Entity
public class Source {
    @Id
    private ObjectId id;
    private String name;
    private String address;
    private String category;
//    TODO: do we have contents field?
//    private List<Content> contents;

    public Source(String name, String address, String category) {
        this.name = name;
        this.address = address;
        this.category = category;
    }

    public Source() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
