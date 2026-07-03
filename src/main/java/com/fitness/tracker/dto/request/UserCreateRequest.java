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
public class UserCreateRequest {

    @NotBlank(message = "Имя обязательно для заполнения")
    @Size(min = 2, max = 50, message = "Имя должно содержать от 2 до 50 символов")
    private String firstName;

    @NotBlank(message = "Фамилия обязательна для заполнения")
    @Size(min = 2, max = 50, message = "Фамилия должна содержать от 2 до 50 символов")
    private String lastName;

    @NotBlank(message = "Email обязателен для заполнения")
    @Email(message = "Введите корректный email адрес")
    @Size(max = 100, message = "Email не должен превышать 100 символов")
    private String email;

    @NotNull(message = "Возраст обязателен для заполнения")
    @Min(value = 14, message = "Возраст должен быть не менее 14 лет")
    @Max(value = 80, message = "Возраст должен быть не более 80 лет")
    private Integer age;

    @NotNull(message = "Вес обязателен для заполнения")
    @Min(value = 35, message = "Вес должен быть не менее 35 кг")
    @Max(value = 250, message = "Вес должен быть не более 250 кг")
    private Double weight;

    @NotNull(message = "Рост обязателен для заполнения")
    @Min(value = 100, message = "Рост должен быть не менее 100 см")
    @Max(value = 250, message = "Рост должен быть не более 250 см")
    private Double height;
}