package main_package.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TypeOfTraining {
    private String name;
    public TypeOfTraining(String type) {
        this.name = type;
    }

    @Override
    public String toString() {
        return "Type of training: " +
                "name= " + "\'" + name + "\'";
    }
}
