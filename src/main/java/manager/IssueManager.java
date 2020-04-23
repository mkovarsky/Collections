package manager;

import domain.Issue;
import lombok.AllArgsConstructor;
import repository.IssueRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
public class IssueManager {
    private IssueRepository repository;

    //1
    public void save(Issue issue) {
        repository.add(issue);
    }

    //2
    public List<Issue> showOpenIssues(boolean open) {
        List<Issue> tmp = new ArrayList<>();
        for (Issue issue : repository.getAll()) {
            if (issue.isOpen() == open) {
                tmp.add(issue);
            }
        }
        return tmp;
    }

    public List<Issue> showClosedIssues(boolean open) {
        List<Issue> tmp = new ArrayList<>();
        for (Issue issue : repository.getAll()) {
            if (issue.isOpen() == open) {
                tmp.add(issue);
            }
        }
        return tmp;
    }

    //3
    public List<Issue> filterByAuthor(String author) {
        List<Issue> tmp = new ArrayList<>();
        for (Issue issue : repository.getAll()) {
            if (issue.getAuthor().equals(author)) {
                tmp.add(issue);
            }
        }
        return tmp;
    }

    public List<Issue> filterByLabel(Set<String> label) {
        List<Issue> tmp = new ArrayList<>();
        for (Issue issue : repository.getAll()) {
            if (issue.getLabels().containsAll(label)) {
                tmp.add(issue);
            }
        }
        return tmp;
    }

    public List<Issue> filterByAssignee(Set<String> assignee) {
        List<Issue> tmp = new ArrayList<>();
        for (Issue issue : repository.getAll()) {
            if (issue.getAssignees().containsAll(assignee)) {
                tmp.add(issue);
            }
        }
        return tmp;
    }

    //5
    public void openById(int id) {
        Issue issueToOpen = repository.getById(id);
        issueToOpen.setOpen(true);
    }

    public void closeById(int id) {
        Issue issueToClose = repository.getById(id);
        issueToClose.setOpen(false);
    }
}
