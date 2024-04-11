package main_package.service;

import main_package.model.TypeOfTraining;
import main_package.repository.InMemoryTypeOfTrainingRepository;
import main_package.repository.TypeOfTrainingRepository;

import java.util.List;

public class SimpleTypeOfTrainingService implements TypeOfTrainingService {
    private final TypeOfTrainingRepository repository = new InMemoryTypeOfTrainingRepository();

    @Override
    public TypeOfTraining findTypeByName(String type) {
        return repository.findTypeOfTrainingByName(type);
    }

    @Override
    public void saveTypeOfTraining(TypeOfTraining type) {
        repository.saveTypeOfTraining(type);
    }

    @Override
    public List<TypeOfTraining> getAllTypes() {
        return repository.getAll();
    }
}
