package service;

import model.TypeOfTraining;
import repository.InMemoryTypeOfTrainingRepository;
import repository.TypeOfTrainingRepository;

public class SimpleTypeOfTrainingService implements TypeOfTrainingService {
    private final TypeOfTrainingRepository repository = new InMemoryTypeOfTrainingRepository();

    @Override
    public TypeOfTraining findTypeByName(String type) {
        return repository.findTypeOfTrainingByName(type);
    }

    @Override
    public void createNewTypeOfTraining(TypeOfTraining type) {
        repository.saveTypeOfTraining(type);
    }
}
