package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;

@Data
@EqualsAndHashCode
@AllArgsConstructor
public class Training {
    private String name;
    private LocalDate date;
    private long durationInMinutes;
    private TypeOfTraining type;
    private long calories;
    private int cycle;
    private int set;

}
