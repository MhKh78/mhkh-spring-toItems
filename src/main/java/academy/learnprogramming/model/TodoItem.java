package academy.learnprogramming.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Setter;

import java.time.LocalDate;

@Data
@EqualsAndHashCode(of = "id")
public class TodoItem  {
    // == fields ==
    private int id;
    private String title;
    private String details;
    private LocalDate deadline;

    // constructors
    public TodoItem(String title, String details, LocalDate deadline) {
        this.title = title;
        this.details = details;
        this.deadline = deadline;
    }

}
