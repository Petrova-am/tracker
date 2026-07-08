package com.fitness.tracker.mapper;

import com.fitness.tracker.dto.request.WorkoutTypeRequest;
import com.fitness.tracker.dto.response.WorkoutTypeResponse;
import com.fitness.tracker.entity.WorkoutType;
import org.springframework.stereotype.Component;

@Component
public class WorkoutTypeMapper {

    public WorkoutType toEntity(WorkoutTypeRequest request) {
        if (request == null) {
            return null;
        }
        return WorkoutType.builder()
                .name(request.getName())
                .metValue(request.getMetValue())
                .build();
    }

    public WorkoutTypeResponse toResponse(WorkoutType workoutType) {
        if (workoutType == null) {
            return null;
        }
        return WorkoutTypeResponse.builder()
                .id(workoutType.getId())
                .name(workoutType.getName())
                .metValue(workoutType.getMetValue())
                .createdAt(workoutType.getCreatedAt())
                .updatedAt(workoutType.getUpdatedAt())
                .build();
    }
    public void updateEntity(WorkoutTypeRequest request, WorkoutType workoutType) {
        if (request == null || workoutType == null) {
            return;
        }

        workoutType.setName(request.getName());
        workoutType.setMetValue(request.getMetValue());
    }
}