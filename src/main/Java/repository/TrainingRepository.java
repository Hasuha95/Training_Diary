package repository;

import model.Training;
import model.TypeOfTraining;

import java.time.LocalDate;

public interface TrainingRepository {
    //    Содержит методы для сохранения, получения,
//    обновления и удаления информации о тренировках.
    void saveTraining(Training training);
    Training findTrainingByDate(LocalDate date);
    Training findTrainingByType(TypeOfTraining type);
    Training findTrainingByCalories(long calories);

}
