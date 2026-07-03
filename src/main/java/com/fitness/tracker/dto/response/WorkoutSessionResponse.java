package com.fitness.tracker.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WorkoutSessionResponse {

    private Long id;
    private Long userId;
    private String userFullName;
    private Long workoutTypeId;
    private String workoutTypeName;
    private Integer durationMinutes;
    private String intensityLevel;
    private Double caloriesBurned;
    private LocalDate workoutDate;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}