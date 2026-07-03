package com.fitness.tracker.mapper;

import com.fitness.tracker.dto.request.UserCreateRequest;
import com.fitness.tracker.dto.request.UserUpdateRequest;
import com.fitness.tracker.dto.response.UserResponse;
import com.fitness.tracker.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public User toEntity(UserCreateRequest request) {
        if (request == null) {
            return null;
        }
        return User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .age(request.getAge())
                .weight(request.getWeight())
                .height(request.getHeight())
                .build();
    }

    public void updateEntity(UserUpdateRequest request, User user) {
        if (request == null || user == null) {
            return;
        }
        if (request.getFirstName() != null) {
            user.setFirstName(request.getFirstName());
        }
        if (request.getLastName() != null) {
            user.setLastName(request.getLastName());
        }
        if (request.getEmail() != null) {
            user.setEmail(request.getEmail());
        }
        if (request.getAge() != null) {
            user.setAge(request.getAge());
        }
        if (request.getWeight() != null) {
            user.setWeight(request.getWeight());
        }
        if (request.getHeight() != null) {
            user.setHeight(request.getHeight());
        }
    }

    public UserResponse toResponse(User user) {
        if (user == null) {
            return null;
        }
        return UserResponse.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .age(user.getAge())
                .weight(user.getWeight())
                .height(user.getHeight())
                .createdAt(user.getCreatedAt())
                .updatedAt(user.getUpdatedAt())
                .build();
    }
}