package ru.netology.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.domain.Issue;
import ru.netology.exception.NotFoundException;

import java.util.List;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class IssueRepositorySingleTest {

    IssueRepository repository = new IssueRepository();

    Issue first = new Issue(2239, "assertTimeoutPreemptively should provide reference to future or executor service parameter", true, "doctorpangloss", asList("component: Jupiter", "status: team discussion", "theme: programming model", "type: enhancement"), emptyList(), "5.7 M2", emptyList(), "07.04.2020", 1, 0);

    @BeforeEach()
    void setup() {
        repository.add(first);
    }

    @Test
    void shouldGetAll() {
        List<Issue> actual = repository.getAll();
        List<Issue> expected = singletonList(first);

        assertEquals(expected, actual);
    }

    @Test
    void shouldGetByExistentId() {
        Issue actual = repository.getById(2239);
        Issue expected = first;

        assertEquals(expected, actual);
    }

    @Test
    void shouldNotGetByNotExistentId() {

        assertThrows(NotFoundException.class, () -> repository.getById(17));
    }
}
