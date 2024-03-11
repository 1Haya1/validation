package org.example.project.Model;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Project {

    @NotNull(message = "Id cannot be null")
    @Size(min = 3, message = "length must be more than 2")
    private long id;

    @NotNull(message = "cannot be null")
    @Size(min = 9, message = "length must be more than 8")
    private String title;

    @NotNull(message = "cannot be null")
    @Size(min = 16, message = "length must be more than 15")
    private String description;

    @NotNull(message = "cannot be null")
    @Pattern(regexp = "^(Not Started|In Progress|Completed)$", message = "Status must be Not Started, In Progress, or Completed only")
    private boolean status;

    @NotNull(message = "name cannot be null")
    @Size(min = 7, message = "name length must be more than 6")
    private String companyName;


}
