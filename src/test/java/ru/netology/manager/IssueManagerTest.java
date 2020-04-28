package ru.netology.manager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.comparator.IssueAscComparator;
import ru.netology.domain.Issue;
import ru.netology.repository.IssueRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;
import static org.junit.jupiter.api.Assertions.assertEquals;

class IssueManagerTest {
    IssueRepository repository = new IssueRepository();
    IssueManager manager = new IssueManager(repository);
    IssueAscComparator comparator = new IssueAscComparator();


    Issue first = new Issue(2239, "assertTimeoutPreemptively should provide reference to future or executor service parameter", true, "doctorpangloss", asList("component: Jupiter", "status: team discussion", "theme: programming model", "type: enhancement"), emptyList(), "5.7 M2", emptyList(), "2020-04-07", 1, 0);
    Issue second = new Issue(2240, "Add icon for JetBrains Toolbox", false, "marcphilipp", asList("status: team discussion", "type: enhancement"), emptyList(), "5.7 M1", emptyList(), "2020-04-10", 29, 1);
    Issue third = new Issue(2241, "GitHub issue links in IDEA Git log", false, "marcphilipp", asList("status: team discussion", "type: enhancement"), emptyList(), "5.7 M1", asList("stranger1", "stranger2", "stranger3"), "2020-04-13", 0, 0);
    Issue fourth = new Issue(2242, "Reuse Launcher orchestration in EngineTestKit", false, "marcphilipp", emptyList(), emptyList(), "5.7 M1 ", singletonList("marcphilipp"), "2020-04-14", 2, 1);
    Issue fifth = new Issue(2243, "Improve module descriptions", true, "sormuras", asList("component: Jupiter", "component: OTA", "component: Platform", "component: Test Kit", "component: Vintage", "theme: documentation"), emptyList(), "5.7 Backlog", asList("stranger1", "stranger2"), "2020-04-15", 4, 1);
    Issue sixth = new Issue(2244, "IsTestableMethod silently ignores @Test annotated methods that return a value", false, "george-hawkins", asList("component: Jupiter", "status: duplicate", "status: team discussion", "theme: diagnostics", "theme: execution", "theme: reporting"), emptyList(), null, emptyList(), "2020-04-16", 3, 0);

    @BeforeEach()
    void setup() {
        manager.save(first);
        manager.save(second);
        manager.save(third);
        manager.save(fourth);
        manager.save(fifth);
        manager.save(sixth);
    }

    @Test
    void shouldShowAllOpenIssues() {
        List<Issue> actual = manager.showOpenIssues();
        List<Issue> expected = asList(first, fifth);

        assertEquals(expected, actual);
    }

    @Test
    void shouldShowAllClosedIssues() {
        List<Issue> actual = manager.showClosedIssues();
        List<Issue> expected = asList(second, third, fourth, sixth);

        assertEquals(expected, actual);
    }

    @Test
    void shouldFilterByAuthor() {
        List<Issue> actual = manager.filterByAuthor(issue -> issue.getAuthor().equals("marcphilipp"), comparator);
        List<Issue> expected = asList(second, third, fourth);

        assertEquals(expected, actual);
    }

    @Test
    void shouldFilterBySingleLabel() {
        List<Issue> actual = manager.filterByLabel(issue -> issue.getLabels().contains("theme: diagnostics"), comparator);
        List<Issue> expected = singletonList(sixth);

        assertEquals(expected, actual);
    }

    @Test
    void shouldFilterByMultiLabel() {
        Set<String> labels = new HashSet<>();
        labels.add("component: Jupiter");
        labels.add("status: team discussion");

        List<Issue> actual = manager.filterByLabel(issue -> issue.getLabels().containsAll(labels), comparator);
        List<Issue> expected = asList(first, sixth);

        assertEquals(expected, actual);
    }

    @Test
    void shouldFilterBySingleAssignee() {
        List<Issue> actual = manager.filterByAssignee(issue -> issue.getAssignees().contains("marcphilipp"), comparator);
        List<Issue> expected = singletonList(fourth);

        assertEquals(expected, actual);
    }

    @Test
    void shouldFilterByMultiAssignee() {
        Set<String> assignees = new HashSet<>();
        assignees.add("stranger1");
        assignees.add("stranger2");

        List<Issue> actual = manager.filterByAssignee(issue -> issue.getAssignees().containsAll(assignees), comparator);
        List<Issue> expected = asList(third, fifth);

        assertEquals(expected, actual);
    }

    @Test
    void shouldOpenById() {
        manager.openById(2240);

        List<Issue> actual = manager.showOpenIssues();
        List<Issue> expected = asList(first, second, fifth);

        assertEquals(expected, actual);

    }

    @Test
    void shouldCloseById() {
        manager.closeById(2239);

        List<Issue> actual = manager.showClosedIssues();
        List<Issue> expected = asList(first, second, third, fourth, sixth);

        assertEquals(expected, actual);
    }
}
