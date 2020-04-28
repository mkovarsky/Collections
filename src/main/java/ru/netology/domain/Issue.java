package ru.netology.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
public class Issue {

    private int id;
    private String name;
    private boolean open;
    private String author;
    private Set<String> labels = new HashSet<>();
    private Set<String> projects = new HashSet<>();
    private String milestone;
    private Set<String> assignees = new HashSet<>();
    private LocalDate date;
    private int commentsCount;
    private int linkedPullRequestCount;

    public Issue(int id, String name, boolean open, String author, Collection<String> labels, Collection<String> projects, String milestone, Collection<String> assignees, String date, int commentsCount, int linkedPullRequestCount) {
        this.id = id;
        this.name = name;
        this.open = open;
        this.author = author;
        this.labels.addAll(labels);
        this.projects.addAll(projects);
        this.milestone = milestone;
        this.assignees.addAll(assignees);
        this.date = LocalDate.parse(date);
        this.commentsCount = commentsCount;
        this.linkedPullRequestCount = linkedPullRequestCount;
    }
}
