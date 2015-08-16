package database;

import com.mongodb.MongoClient;
import content.Relationship;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.dao.BasicDAO;

/**
 * @author Hamid Ramazani (mrramazani@gmail.com)
 *         Created on 8/6/2015
 */
public class RelationshipRepository extends BasicDAO<Relationship, String> {
    public RelationshipRepository(Morphia morphia, MongoClient mongo, String dbName) {
        super(mongo, morphia, dbName);
    }
}
