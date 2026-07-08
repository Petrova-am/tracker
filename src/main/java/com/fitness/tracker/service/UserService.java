package com.fitness.tracker.service;

import com.fitness.tracker.dto.request.UserCreateRequest;
import com.fitness.tracker.dto.request.UserUpdateRequest;
import com.fitness.tracker.dto.response.UserResponse;

public interface UserService {

    UserResponse createUser(UserCreateRequest request);

    UserResponse getUser(Long id);

    UserResponse updateUser(Long id, UserUpdateRequest request);

    void deleteUser(Long id);
}