package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class Training {
    private String name;
    private LocalDateTime date;
    private long durationInMinutes;
    private TypeOfTraining type;
    private long calories;
    private int cycles;
    private int sets;

}
