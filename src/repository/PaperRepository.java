package repository;

import model.Paper;
import java.util.List;

public interface PaperRepository {

    void addPaper(Paper paper);
    Paper getPaperById(int id);
    List<Paper> getAllPapers();
    void updatePaper(Paper paper);
    void deletePaper(int id);
}
