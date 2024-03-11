package org.example.event.Model;


import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;


import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class Event {

    @NotNull(message = "Id cannot be null")
    @Size(min = 3, message = "Id length must be more than 2")
    private long id;

    @NotNull(message = "Description cannot be null")
    @Size(min = 16, message = "must be more than 15")
    private String description;

    @NotNull(message = "Capacity cannot be null")
    @Min(value = 26, message = "must be more than 25")
    private int capacity;

    @NotNull(message = "Start date cannot be null")
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDateTime startDate;

    @NotNull(message = "End date cannot be null")
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDateTime endDate;




}
