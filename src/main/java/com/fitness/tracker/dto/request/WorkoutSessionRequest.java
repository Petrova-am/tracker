package com.fitness.tracker.dto.request;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WorkoutSessionRequest {

    @NotNull(message = "ID пользователя обязателен")
    private Long userId;

    @NotNull(message = "ID типа тренировки обязателен")
    private Long workoutTypeId;

    @NotNull(message = "Длительность тренировки обязательна")
    @Min(value = 5, message = "Длительность должна быть не менее 5 минут")
    @Max(value = 180, message = "Длительность должна быть не более 180 минут")
    private Integer durationMinutes;

    @NotBlank(message = "Интенсивность обязательна")
    @Pattern(
            regexp = "LOW|MODERATE|HIGH|INTENSE",
            message = "Интенсивность должна быть: LOW, MODERATE, HIGH или INTENSE"
    )
    private String intensity;

    @NotNull(message = "Дата тренировки обязательна")
    @PastOrPresent(message = "Дата тренировки не может быть в будущем")
    private LocalDate workoutDate;
}