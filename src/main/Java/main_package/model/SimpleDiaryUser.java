package main_package.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import main_package.UserRole;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Data
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class SimpleDiaryUser extends DiaryUser{
    private String name;
    private String userName;
    private String password;
    private UserRole role;
    private List<Training> trainings;

    public SimpleDiaryUser(String name, String userName, String password, UserRole role) {
        this.name = name;
        this.userName = userName;
        this.password = password;
        this.role = role;
        trainings = new ArrayList<>();
    }

    public void addTraining(Training training) {
        trainings.add(training);
    }

    public void deleteTraining(Training training) {
        trainings.remove(training);
    }

    public List<Training> getDalyTrainings(LocalDate localDate){
        return trainings
                .stream()
                .filter(t -> LocalDate.from(t.getDate()).equals(localDate))
                .collect(Collectors.toList());
    }
}
