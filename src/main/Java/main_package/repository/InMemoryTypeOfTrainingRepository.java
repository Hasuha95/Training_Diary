package main_package.repository;

import main_package.model.TypeOfTraining;

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
        for (TypeOfTraining t : list) {
            if (t.getName().equals(name)){
                return t;
            }
        }
        return null;
    }

    @Override
    public List<TypeOfTraining> getAll() {
        return list;
    }
}
