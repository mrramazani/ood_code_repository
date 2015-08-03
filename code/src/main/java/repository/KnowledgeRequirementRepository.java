package repository;

import com.mongodb.MongoClient;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.dao.BasicDAO;
import source.KnowledgeRequirement;

/**
 * @author Hamid Ramazani (mrramazani@gmail.com)
 *         Created on 7/31/2015
 */
public class KnowledgeRequirementRepository extends BasicDAO<KnowledgeRequirement, String>{

    public KnowledgeRequirementRepository(MongoClient mongoClient, Morphia morphia, String dbName) {
        super(mongoClient, morphia, dbName);
    }
}
