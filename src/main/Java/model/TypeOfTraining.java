package model;

import lombok.Getter;

@Getter
public class TypeOfTraining {
    private final String name;

    public TypeOfTraining(String type) {
        this.name = type;
    }
}
