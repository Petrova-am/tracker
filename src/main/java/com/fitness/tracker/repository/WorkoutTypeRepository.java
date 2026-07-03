package com.fitness.tracker.repository;

import com.fitness.tracker.entity.WorkoutType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WorkoutTypeRepository extends JpaRepository<WorkoutType, Long> {

    Optional<WorkoutType> findByName(String name);

    boolean existsByName(String name);

    List<WorkoutType> findAllByOrderByNameAsc();
}