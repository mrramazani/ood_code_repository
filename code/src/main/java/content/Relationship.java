package content;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Reference;

/**
 * @author Hamid Ramazani (mrramazani@gmail.com)
 *         Created on 7/21/2015
 */
@Entity
public class Relationship {
    @Id
    private ObjectId id;
    @Reference
    private Content firstContent;
    @Reference
    private Content secondContent;
    private RelationShipType type;

    public Relationship(Content firstContent, Content secondContent, RelationShipType type) {
        this.firstContent = firstContent;
        this.secondContent = secondContent;
        this.type = type;
    }

    public ObjectId getId() {
        return id;
    }

    public Content getFirstContent() {
        return firstContent;
    }

    public void setFirstContent(Content firstContent) {
        this.firstContent = firstContent;
    }

    public Content getSecondContent() {
        return secondContent;
    }

    public void setSecondContent(Content secondContent) {
        this.secondContent = secondContent;
    }

    public RelationShipType getType() {
        return type;
    }

    public void setType(RelationShipType type) {
        this.type = type;
    }

}
