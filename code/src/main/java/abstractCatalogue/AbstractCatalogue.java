package abstractCatalogue;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import content.Comment;
import content.Content;
import content.Relationship;
import org.mongodb.morphia.Morphia;
import user.User;

import java.net.UnknownHostException;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Hamid Ramazani (mrramazani@gmail.com)
 *         Created on 7/26/2015
 */
public abstract class AbstractCatalogue {

    private Morphia morphia;
    private MongoClient mongoClient;

    public AbstractCatalogue() {
        Set classesToMap = new HashSet();
        classesToMap.add(Content.class);
        classesToMap.add(User.class);
        classesToMap.add(Comment.class);
        classesToMap.add(Relationship.class);
        morphia = new Morphia(classesToMap);
        try {
            mongoClient = new MongoClient("localhost", 27017);
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }
    }

    public Morphia getMorphia() {
        return morphia;
    }

    public void setMorphia(Morphia morphia) {
        this.morphia = morphia;
    }

    public MongoClient getMongoClient() {
        return mongoClient;
    }

    public void setMongoClient(MongoClient mongoClient) {
        this.mongoClient = mongoClient;
    }
}
