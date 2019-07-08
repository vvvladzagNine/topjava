package ru.javawebinar.topjava.repository.datajpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.util.DateTimeUtil;

import java.time.LocalDateTime;
import java.util.List;

public interface CrudMealRepository extends JpaRepository<Meal, Integer> {
    @Transactional
    @Modifying
    @Query("DELETE FROM Meal m WHERE m.id=:id AND m.user.id=:userId")
    int delete(@Param("id")int id,@Param("userId")int userId);

    Meal findByIdAndUser(int id, User user);

    @Transactional
    @Modifying
    @Query("SELECT m from Meal m WHERE m.dateTime>:start AND m.dateTime<:end AND m.user.id=:userId order by m.dateTime desc ")
    List<Meal> getBetween(@Param("start") LocalDateTime start, @Param("end")LocalDateTime end,@Param("userId")int userId);
}
