package com.fitness.tracker.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StatisticsResponse {

    private Long userId;
    private String userFullName;
    private Long totalWorkouts;
    private Integer totalMinutes;
    private Double totalCalories;
    private Double averageCaloriesPerWorkout;
}