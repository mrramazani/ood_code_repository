package repository;

import com.mongodb.MongoClient;
import content.Content;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.dao.BasicDAO;
import org.mongodb.morphia.query.Query;

/**
 * @author Hamid Ramazani (mrramazani@gmail.com)
 *         Created on 7/25/2015
 */
public class ContentRepository extends BasicDAO<Content, String> {


    public ContentRepository(Morphia morphia, MongoClient mongo) {
        super(mongo, morphia, "test");
    }

    public Content search(String name) {
        Query<Content> query = createQuery().field("name").equal(name);
        return findOne(query);
    }
//    private List<Content> contents = new ArrayList<Content>();
//
//    public List<Content> getContents() {
//        return contents;
//    }
//
//    public void setContents(List<Content> contents) {
//        this.contents = contents;
//    }
}

