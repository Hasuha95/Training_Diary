package repository;

import model.TypeOfTraining;

import java.util.ArrayList;
import java.util.List;

public class InMemoryTypeOfTrainingRepository implements TypeOfTrainingRepository{
    private List<TypeOfTraining> list;

    public InMemoryTypeOfTrainingRepository() {
        this.list = new ArrayList<>();
    }

    @Override
    public void saveTypeOfTraining(TypeOfTraining type) {
        list.add(type);
    }

    @Override
    public TypeOfTraining findTypeOfTrainingByName(String name) {
        return null;
    }
}
