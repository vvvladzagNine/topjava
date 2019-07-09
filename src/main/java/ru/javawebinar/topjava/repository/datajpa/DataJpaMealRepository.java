package ru.javawebinar.topjava.repository.datajpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public class DataJpaMealRepository implements MealRepository {
    //private static final Sort SORT_DATETIME = new Sort(Sort.Direction.DESC, "dateTime");


    @Autowired
    private CrudMealRepository crudRepository;

    @Autowired
    private CrudUserRepository userRepository;

    @Override
    public Meal save(Meal meal, int userId) {

        /*
        Optional<User> u = userRepository.findById(userId);
        if(meal.isNew()) {
            meal.setUser(u.get());
            crudRepository.save(meal);
            return meal;
        }else {
            if(get(meal.getId(),userId)!=null){
                meal.setUser(u.get());
                crudRepository.save(meal);
                return meal;
            }
            return null;
        }
         */
        if (!meal.isNew() && get(meal.getId(), userId) == null) {
            return null;
        }
        else{
            meal.setUser(userRepository.getOne(userId));
            crudRepository.save(meal);
            return meal;
        }
    }

    @Override
    public boolean delete(int id, int userId) {
        return crudRepository.delete(id,userId)!=0;
    }

    @Override
    public Meal get(int id, int userId) {
       // User u = userRepository.findById(userId).get();
        return crudRepository.getByIdAndUserId(userId,id);
    }

    @Override
    public List<Meal> getAll(int userId) {
        return crudRepository.findAllByUserId(userId);
    }

    @Override
    public List<Meal> getBetween(LocalDateTime startDate, LocalDateTime endDate, int userId) {

       return crudRepository.getBetween(startDate,endDate,userId);

    }
}
