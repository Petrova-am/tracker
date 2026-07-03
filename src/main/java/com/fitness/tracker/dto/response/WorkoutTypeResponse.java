package com.fitness.tracker.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WorkoutTypeResponse {

    private Long id;
    private String name;
    private String description;
    private Double metValue;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}