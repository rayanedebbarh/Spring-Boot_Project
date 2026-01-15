package repository;

import model.Paper;
import java.util.ArrayList;
import java.util.List;

public class PaperRepositoryImpl implements PaperRepository {

    private List<Paper> papers = new ArrayList<>();

    @Override
    public void addPaper(Paper paper) {
        papers.add(paper);
    }

    @Override
    public Paper getPaperById(int id) {
        for (Paper paper : papers) {
            if (paper.getId() == id) return paper;
        }
        return null;
    }

    @Override
    public List<Paper> getAllPapers() {
        return papers;
    }

    @Override
    public void updatePaper(Paper updatedPaper) {
        for (int i = 0; i < papers.size(); i++) {
            if (papers.get(i).getId() == updatedPaper.getId()) {
                papers.set(i, updatedPaper);
                return;
            }
        }
    }

    @Override
    public void deletePaper(int id) {
        papers.removeIf(paper -> paper.getId() == id);
    }
}
