package model;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TypeOfTraining {
    private String name;

    public TypeOfTraining(String type) {
        this.name = type;
    }
}
