package ru.netology.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.netology.domain.Issue;
import ru.netology.exception.NotFoundException;

import java.util.ArrayList;
import java.util.List;

public class IssueRepositoryEmptyTest {
    private IssueRepository repository = new IssueRepository();

    @Test
    void shouldGetAll() {
        List<Issue> actual = repository.getAll();
        List<Issue> expected = new ArrayList<>();

        Assertions.assertEquals(expected, actual);
    }

    @Test
    void shouldGetByExistentId() {

        Assertions.assertThrows(NotFoundException.class, () -> repository.getById(14));
    }

}
