package com.fitness.tracker.service.impl;

import com.fitness.tracker.dto.request.UserCreateRequest;
import com.fitness.tracker.dto.request.UserUpdateRequest;
import com.fitness.tracker.dto.response.UserResponse;
import com.fitness.tracker.entity.User;
import com.fitness.tracker.exception.ResourceNotFoundException;
import com.fitness.tracker.exception.ValidationException;
import com.fitness.tracker.mapper.UserMapper;
import com.fitness.tracker.repository.UserRepository;
import com.fitness.tracker.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public UserResponse createUser(UserCreateRequest request) {
        log.info("Creating new user with email: {}", request.getEmail());

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new ValidationException("Пользователь с таким email уже существует: " + request.getEmail());
        }

        User user = userMapper.toEntity(request);

        User savedUser = userRepository.save(user);
        log.info("User created with id: {}", savedUser.getId());

        return userMapper.toResponse(savedUser);
    }

    @Override
    public UserResponse getUser(Long id) {
        log.info("Fetching user with id: {}", id);

        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User", id));

        return userMapper.toResponse(user);
    }

    @Override
    public UserResponse updateUser(Long id, UserUpdateRequest request) {
        log.info("Updating user with id: {}", id);

        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User", id));

        if (request.getEmail() != null && !request.getEmail().equals(user.getEmail())) {
            if (userRepository.existsByEmail(request.getEmail())) {
                throw new ValidationException("Пользователь с таким email уже существует: " + request.getEmail());
            }
        }

        userMapper.updateEntity(request, user);

        User updatedUser = userRepository.save(user);
        log.info("User updated with id: {}", updatedUser.getId());

        return userMapper.toResponse(updatedUser);
    }

    @Override
    public void deleteUser(Long id) {
        log.info("Deleting user with id: {}", id);

        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User", id));

        userRepository.delete(user);
        log.info("User deleted with id: {}", id);
    }
}