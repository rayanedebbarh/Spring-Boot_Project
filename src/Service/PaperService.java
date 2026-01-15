package Service;

import data.PaperRepository;
import model.Paper;
import java.util.List;

public class PaperService {

    private PaperRepository repository;

    public PaperService(PaperRepository repository) {
        this.repository = repository;
    }

    public void save(Paper paper) { repository.save(paper); }
    public void update(Paper paper) { repository.update(paper); }
    public void delete(int id) { repository.delete(id); }
    public Paper findById(int id) { return repository.findById(id); }
    public List<Paper> findAll() { return repository.findAllByOrderByTitle(); }
}
