package repository;

import model.TypeOfTraining;

public interface TypeOfTrainingRepository {
    void saveTypeOfTraining(TypeOfTraining type);
    TypeOfTraining findTypeOfTrainingByName(String name);
}
