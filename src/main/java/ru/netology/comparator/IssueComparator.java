package ru.netology.comparator;

import ru.netology.domain.Issue;

import java.util.Comparator;

public class IssueComparator implements Comparator<Issue> {

    public IssueComparator() {
    }

    public int compare(Issue o1, Issue o2) {
        if (o1.getId() > o2.getId()) {
            return -1;
        }
        if (o1.getId() < o2.getId()) {
            return 1;
        }
        return 0;
    }
}
