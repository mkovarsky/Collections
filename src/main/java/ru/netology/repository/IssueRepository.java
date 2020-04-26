package ru.netology.repository;

import ru.netology.domain.Issue;
import ru.netology.exception.NotFoundException;

import java.util.ArrayList;
import java.util.List;

public class IssueRepository {
    List<Issue> issues = new ArrayList<>();

    public void add(Issue issue) {
        issues.add(issue);
    }

    public List<Issue> getAll() {
        return issues;
    }

    public Issue getById(int id) {
        for (Issue issue : issues) {
            if (issue.getId() == id) {
                return issue;
            }
        }
        throw new NotFoundException("Element with id: " + id + " not found.");
    }

    public List<Issue> getByStatus(boolean open) {
        List<Issue> tmp = new ArrayList<>();
        for (Issue issue : issues) {
            if (issue.isOpen() == open) {
                tmp.add(issue);
            }
        }
        return tmp;
    }
}
