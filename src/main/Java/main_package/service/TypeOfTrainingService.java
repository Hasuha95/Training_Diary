package main_package.service;

import main_package.model.TypeOfTraining;

import java.util.List;

public interface TypeOfTrainingService {
    TypeOfTraining findTypeByName(String type);

    void saveTypeOfTraining(TypeOfTraining type);

    List<TypeOfTraining> getAllTypes();
}
