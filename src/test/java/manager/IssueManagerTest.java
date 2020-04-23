package manager;

import domain.Issue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import repository.IssueRepository;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

class IssueManagerTest {
    IssueRepository repository = new IssueRepository();
    IssueManager manager = new IssueManager(repository);


    Issue first = new Issue(2239, "assertTimeoutPreemptively should provide reference to future or executor service parameter", true, "doctorpangloss", Arrays.asList("component: Jupiter", "status: team discussion", "theme: programming model", "type: enhancement"), Collections.emptyList(), "5.7 M2", Collections.emptyList(), "07.04.2020", 1, 0);
    Issue second = new Issue(2240, "Add icon for JetBrains Toolbox", false, "marcphilipp", Arrays.asList("status: team discussion", "type: enhancement"), Collections.emptyList(), "5.7 M1", Collections.emptyList(), "10.04.2020", 29, 1);
    Issue third = new Issue(2241, "GitHub issue links in IDEA Git log", false, "marcphilipp", Arrays.asList("status: team discussion", "type: enhancement"), Collections.emptyList(), "5.7 M1", Arrays.asList("stranger1", "stranger2", "stranger3"), "13.04.2020", 0, 0);
    Issue fourth = new Issue(2242, "Reuse Launcher orchestration in EngineTestKit", false, "marcphilipp", Collections.emptyList(), Collections.emptyList(), "5.7 M1 ", Collections.singletonList("marcphilipp"), "14.04.2020", 2, 1);
    Issue fifth = new Issue(2243, "Improve module descriptions", true, "sormuras", Arrays.asList("component: Jupiter", "component: OTA", "component: Platform", "component: Test Kit", "component: Vintage", "theme: documentation"), Collections.emptyList(), "5.7 Backlog", Arrays.asList("stranger1", "stranger2"), "15.04.2020", 4, 1);
    Issue sixth = new Issue(2244, "IsTestableMethod silently ignores @Test annotated methods that return a value", false, "george-hawkins", Arrays.asList("component: Jupiter", "status: duplicate", "status: team discussion", "theme: diagnostics", "theme: execution", "theme: reporting"), Collections.emptyList(), null, Collections.emptyList(), "16.04.2020", 3, 0);

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
        List<Issue> actual = manager.showOpenIssues(true);
        List<Issue> expected = Arrays.asList(first, fifth);

        assertEquals(expected, actual);
    }

    @Test
    void shouldShowAllClosedIssues() {
        List<Issue> actual = manager.showClosedIssues(false);
        List<Issue> expected = Arrays.asList(second, third, fourth, sixth);

        assertEquals(expected, actual);
    }

    @Test
    void shouldFilterByAuthor() {
        List<Issue> actual = manager.filterByAuthor("marcphilipp");
        List<Issue> expected = Arrays.asList(second, third, fourth);

        assertEquals(expected, actual);
    }

    @Test
    void shouldFilterBySingleLabel() {
        List<Issue> actual = manager.filterByLabel(Collections.singleton("theme: diagnostics"));
        List<Issue> expected = Collections.singletonList(sixth);

        assertEquals(expected, actual);
    }

    @Test
    void shouldFilterByMultiLabel() {
        Set<String> labels = new HashSet<>();
        labels.add("component: Jupiter");
        labels.add("status: team discussion");

        List<Issue> actual = manager.filterByLabel(labels);
        List<Issue> expected = Arrays.asList(first, sixth);

        assertEquals(expected, actual);
    }

    @Test
    void shouldFilterBySingleAssignee() {
        List<Issue> actual = manager.filterByAssignee(Collections.singleton("marcphilipp"));
        List<Issue> expected = Collections.singletonList(fourth);

        assertEquals(expected, actual);
    }

    @Test
    void shouldFilterByMultiAssignee() {
        Set<String> assignees = new HashSet<>();
        assignees.add("stranger1");
        assignees.add("stranger2");

        List<Issue> actual = manager.filterByAssignee(assignees);
        List<Issue> expected = Arrays.asList(third, fifth);

        assertEquals(expected, actual);
    }

    @Test
    void shouldOpenById() {
        manager.openById(2240);

        List<Issue> actual = manager.showOpenIssues(true);
        List<Issue> expected = Arrays.asList(first, second, fifth);

        assertEquals(expected, actual);

    }

    @Test
    void shouldCloseById() {
        manager.closeById(2239);

        List<Issue> actual = manager.showClosedIssues(false);
        List<Issue> expected = Arrays.asList(first, second, third, fourth, sixth);

        assertEquals(expected, actual);
    }
}
