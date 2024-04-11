package model;

import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
@EqualsAndHashCode
public class DiaryUser {
    private String name;
    private String userName;
    private String password;
    private String role;

    public DiaryUser(String name, String userName, String password, String role) {
        this.name = name;
        this.userName = userName;
        this.password = password;
        this.role = role;
    }
}
