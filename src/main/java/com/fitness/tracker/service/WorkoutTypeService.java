package com.fitness.tracker.service;

import com.fitness.tracker.dto.request.WorkoutTypeRequest;
import com.fitness.tracker.dto.response.WorkoutTypeResponse;

import java.util.List;

public interface WorkoutTypeService {

    WorkoutTypeResponse createWorkoutType(WorkoutTypeRequest request);

    WorkoutTypeResponse getWorkoutType(Long id);

    List<WorkoutTypeResponse> getAllWorkoutTypes();

    WorkoutTypeResponse updateWorkoutType(Long id, WorkoutTypeRequest request);

    void deleteWorkoutType(Long id);
}