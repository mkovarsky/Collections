package repository;

import domain.Issue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;


class IssueRepositoryTest {

    IssueRepository repository = new IssueRepository();

    Issue first = new Issue(2239, "assertTimeoutPreemptively should provide reference to future or executor service parameter", true, "doctorpangloss", Arrays.asList("component: Jupiter", "status: team discussion", "theme: programming model", "type: enhancement"), Collections.emptyList(), "5.7 M2", Collections.emptyList(), "07.04.2020", 1, 0);
    Issue second = new Issue(2240, "Add icon for JetBrains Toolbox", false, "marcphilipp", Arrays.asList("status: team discussion", "type: enhancement"), Collections.emptyList(), "5.7 M1", Collections.emptyList(), "10.04.2020", 29, 1);
    Issue third = new Issue(2241, "GitHub issue links in IDEA Git log", false, "marcphilipp", Arrays.asList("status: team discussion", "type: enhancement"), Collections.emptyList(), "5.7 M1", Arrays.asList("stranger1", "stranger2", "stranger3"), "13.04.2020", 0, 0);
    Issue fourth = new Issue(2242, "Reuse Launcher orchestration in EngineTestKit", false, "marcphilipp", Collections.emptyList(), Collections.emptyList(), "5.7 M1 ", Collections.singletonList("marcphilipp"), "14.04.2020", 2, 1);
    Issue fifth = new Issue(2243, "Improve module descriptions", true, "sormuras", Arrays.asList("component: Jupiter", "component: OTA", "component: Platform", "component: Test Kit", "component: Vintage", "theme: documentation"), Collections.emptyList(), "5.7 Backlog", Arrays.asList("stranger1", "stranger2"), "15.04.2020", 4, 1);
    Issue sixth = new Issue(2244, "IsTestableMethod silently ignores @Test annotated methods that return a value", false, "george-hawkins", Arrays.asList("component: Jupiter", "status: duplicate", "status: team discussion", "theme: diagnostics", "theme: execution", "theme: reporting"), Collections.emptyList(), null, Collections.emptyList(), "16.04.2020", 3, 0);

    @BeforeEach()
    void setup() {
        repository.add(first);
        repository.add(second);
        repository.add(third);
        repository.add(fourth);
        repository.add(fifth);
        repository.add(sixth);
    }

    @Test
    void shouldGetAll() {
        List<Issue> actual = repository.getAll();
        List<Issue> expected = Arrays.asList(first, second, third, fourth, fifth, sixth);

        assertEquals(expected, actual);
    }

    @Test
    void shouldGetByExistentId() {
        Issue actual = repository.getById(2240);
        Issue expected = second;

        assertEquals(expected, actual);
    }

    @Test
    void shouldNotGetByNotExistentId() {
        Issue actual = repository.getById(2250);

        assertNull(actual);
    }
}
