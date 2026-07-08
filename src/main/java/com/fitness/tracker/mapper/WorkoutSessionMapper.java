package com.fitness.tracker.mapper;

import com.fitness.tracker.dto.request.WorkoutSessionRequest;
import com.fitness.tracker.dto.response.WorkoutSessionResponse;
import com.fitness.tracker.entity.WorkoutSession;
import com.fitness.tracker.enums.IntensityLevel;
import org.springframework.stereotype.Component;

@Component
public class WorkoutSessionMapper {

    public WorkoutSession toEntity(WorkoutSessionRequest request) {
        if (request == null) {
            return null;
        }
        return WorkoutSession.builder()
                .durationMinutes(request.getDurationMinutes())
                .intensity(IntensityLevel.valueOf(request.getIntensity().toUpperCase()))
                .workoutDate(request.getWorkoutDate())
                .build();
    }

    public WorkoutSessionResponse toResponse(WorkoutSession session) {
        if (session == null) {
            return null;
        }
        return WorkoutSessionResponse.builder()
                .id(session.getId())
                .userId(session.getUser() != null ? session.getUser().getId() : null)
                .userFullName(session.getUser() != null ? session.getUser().getFirstName() + " " + session.getUser().getLastName() : null)
                .workoutTypeId(session.getWorkoutTypeId() != null ? session.getWorkoutTypeId().getId() : null)
                .workoutTypeName(session.getWorkoutTypeId() != null ? session.getWorkoutTypeId().getName() : null)
                .durationMinutes(session.getDurationMinutes())
                .intensityLevel(session.getIntensity() != null ? session.getIntensity().name() : null)
                .caloriesBurned(session.getCaloriesBurned())
                .workoutDate(session.getWorkoutDate())
                .createdAt(session.getCreatedAt())
                .updatedAt(session.getUpdatedAt())
                .build();
    }


}