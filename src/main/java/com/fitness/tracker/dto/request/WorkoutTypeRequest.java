package com.fitness.tracker.dto.request;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WorkoutTypeRequest {

    @NotBlank(message = "Название типа тренировки обязательно")
    @Size(min = 2, max = 100, message = "Название должно содержать от 2 до 100 символов")
    private String name;

    private String description;

    @NotNull(message = "MET коэффициент обязателен")
    @Positive(message = "MET коэффициент должен быть больше 0")
    @DecimalMin(value = "0.1", message = "MET коэффициент должен быть больше 0")
    private Double metValue;
}