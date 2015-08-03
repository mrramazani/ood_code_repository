package repository;

import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import content.Comment;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.dao.BasicDAO;

/**
 * @author Hamid Ramazani (mrramazani@gmail.com)
 *         Created on 7/23/2015
 */
public class CommentRepository extends BasicDAO<Comment, String>{
//    DBCollection commentCollection =

    public CommentRepository(Morphia morphia, MongoClient mongo) {
        super(mongo, morphia, "test");
    }

}
