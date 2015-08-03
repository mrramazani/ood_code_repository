package content;

import abstractCatalogue.AbstractCatalogue;
import repository.ContentChangeRepository;
import repository.ContentRepository;

import java.util.List;

/**
 * @author Hamid Ramazani (mrramazani@gmail.com)
 *         Created on 7/23/2015
 */
public class ContentCatalogue extends AbstractCatalogue {

    private ContentRepository contentRepository;
    private ContentChangeRepository contentChangeRepository;

    public ContentCatalogue() {
        super();
        contentRepository = new ContentRepository(super.getMorphia(), super.getMongoClient());
        contentChangeRepository = new ContentChangeRepository(super.getMorphia(), super.getMongoClient(), "test");
    }

    public void addContent(Content content) {
        contentRepository.save(content);
    }

    public void log(ContentChangeLog log) {
        contentChangeRepository.save(log);
    }

    public List<ContentChangeLog> getLog(String contentName) {
        Content search = contentRepository.search(contentName);
        return contentChangeRepository.getLogForContent(search);
    }

    public Content search(String name) {
        return contentRepository.search(name);
    }

}
