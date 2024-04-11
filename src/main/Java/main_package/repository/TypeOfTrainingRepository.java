package main_package.repository;

import main_package.model.TypeOfTraining;

import java.util.List;

public interface TypeOfTrainingRepository {
    void saveTypeOfTraining(TypeOfTraining type);
    TypeOfTraining findTypeOfTrainingByName(String name);
    List<TypeOfTraining> getAll();
}
