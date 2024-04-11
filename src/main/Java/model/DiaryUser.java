package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


@Data
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class DiaryUser {
    private String name;
    private String userName;
    private String password;
    private String role;
    private List<Training> trainings;

    public DiaryUser(String name, String userName, String password, String role) {
        this.name = name;
        this.userName = userName;
        this.password = password;
        this.role = role;
        trainings = new ArrayList<>();
    }
}
