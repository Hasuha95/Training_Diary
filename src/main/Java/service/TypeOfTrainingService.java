package service;

import model.TypeOfTraining;

public interface TypeOfTrainingService {
    TypeOfTraining findTypeByName(String type);

    void createNewTypeOfTraining(TypeOfTraining type);
}
