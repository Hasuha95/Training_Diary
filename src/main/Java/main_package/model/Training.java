package main_package.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;

@Data
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class Training implements Comparable<Training>{
    private String name;
    private LocalDateTime date;
    private long durationInMinutes;
    private TypeOfTraining type;
    private long calories;
    private int cycles;
    private int sets;

    @Override
    public int compareTo(Training t) {
        return date.compareTo(t.getDate());
    }

    @Override
    public String toString() {
        return "Training:" +
                "\nname= " + "\'" + name + "\'" +
                "\ndate=" + date +
                "\ndurationInMinutes= " + durationInMinutes +
                "\ntype= " + type +
                "\ncalories= " + calories +
                "\ncycles= " + cycles +
                "\nsets= " + sets;
    }
}
