package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


@Data
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class DiaryUser {
    private String name;
    private String userName;
    private String password;
    private String role;
}
