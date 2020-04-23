package domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;
import java.util.HashSet;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Issue {

    private int id;
    private String name;
    private boolean open;
    private String author;
    private Collection<String> labels = new HashSet<>();
    private Collection<String> projects = new HashSet<>();
    private String milestone;
    private Collection<String> assignees = new HashSet<>();
    private String date;
    private int commentsCount;
    private int linkedPullRequestCount;

}
