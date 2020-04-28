package ru.netology.manager;

import ru.netology.domain.Issue;
import lombok.AllArgsConstructor;
import ru.netology.repository.IssueRepository;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

@AllArgsConstructor
public class IssueManager {
    private IssueRepository repository;

    public void save(Issue issue) {
        repository.add(issue);
    }

    public List<Issue> showOpenIssues() {
        return new ArrayList<>(repository.getByStatus(true));
    }

    public List<Issue> showClosedIssues() {
        return new ArrayList<>(repository.getByStatus(false));
    }

    public List<Issue> filterBy(Predicate<Issue> predicate, Comparator<Issue> comparator) {
        List<Issue> tmp = new ArrayList<>();
        for (Issue issue : repository.getAll()) {
            if (predicate.test(issue)) {
                tmp.add(issue);
            }
        }
        tmp.sort(comparator);
        return tmp;
    }

    public void openById(int id) {
        Issue issueToOpen = repository.getById(id);
        issueToOpen.setOpen(true);
    }

    public void closeById(int id) {
        Issue issueToClose = repository.getById(id);
        issueToClose.setOpen(false);
    }
}
