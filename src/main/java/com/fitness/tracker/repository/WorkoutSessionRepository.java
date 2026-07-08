package com.fitness.tracker.repository;

import com.fitness.tracker.entity.WorkoutSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface WorkoutSessionRepository extends JpaRepository<WorkoutSession, Long> {

    List<WorkoutSession> findByUserId(Long userId);

    @Query("SELECT SUM(w.durationMinutes) FROM WorkoutSession w WHERE w.user.id = :userId")
    Integer sumDurationByUserId(@Param("userId") Long userId);

    @Query("SELECT SUM(w.caloriesBurned) FROM WorkoutSession w WHERE w.user.id = :userId")
    Double sumCaloriesByUserId(@Param("userId") Long userId);

    @Query("SELECT COUNT(w) FROM WorkoutSession w WHERE w.user.id = :userId")
    Long countByUserId(@Param("userId") Long userId);
}