package source;

import abstractCatalogue.AbstractCatalogue;
import repository.KnowledgeRequirementRepository;
import repository.SourceRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Hamid Ramazani (mrramazani@gmail.com)
 *         Created on 7/27/2015
 */
public class SourceCatalogue extends AbstractCatalogue{
    private List<Source> sources = new ArrayList<Source>();
    private SourceRepository sourceRepository;
    private KnowledgeRequirementRepository requirementRepository;

    public SourceCatalogue() {
        super();
        sourceRepository = new SourceRepository(this.getMongoClient(), this.getMorphia(), "test");
        requirementRepository = new KnowledgeRequirementRepository(this.getMongoClient(), this.getMorphia(), "test");
    }

    public List<Source> list() {
        return sourceRepository.find().asList();
    }

    public void addSource(Source src) {
        sourceRepository.save(src);
    }

    public void addKnowledgeReq(KnowledgeRequirement kr) {
        requirementRepository.save(kr);
    }

    public void deleteSource(Source src) {
        sourceRepository.delete(src);
    }

    public List<Source> search(String keyword) {
        return sourceRepository.search(keyword);
    }

}
