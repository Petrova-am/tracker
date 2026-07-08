package com.fitness.tracker.service.impl;

import com.fitness.tracker.dto.request.WorkoutSessionRequest;
import com.fitness.tracker.dto.response.StatisticsResponse;
import com.fitness.tracker.dto.response.WorkoutSessionResponse;
import com.fitness.tracker.entity.User;
import com.fitness.tracker.entity.WorkoutSession;
import com.fitness.tracker.entity.WorkoutType;
import com.fitness.tracker.enums.IntensityLevel;
import com.fitness.tracker.exception.ResourceNotFoundException;
import com.fitness.tracker.exception.ValidationException;
import com.fitness.tracker.repository.UserRepository;
import com.fitness.tracker.repository.WorkoutSessionRepository;
import com.fitness.tracker.repository.WorkoutTypeRepository;
import com.fitness.tracker.service.WorkoutSessionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class WorkoutSessionServiceImpl implements WorkoutSessionService {

    private final WorkoutSessionRepository workoutSessionRepository;
    private final UserRepository userRepository;
    private final WorkoutTypeRepository workoutTypeRepository;

    @Override
    @Transactional
    public WorkoutSessionResponse createSession(WorkoutSessionRequest request) {
        log.info("Creating workout session for user id: {}", request.getUserId());

        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + request.getUserId()));

        WorkoutType workoutType = workoutTypeRepository.findById(request.getWorkoutTypeId())
                .orElseThrow(() -> new ResourceNotFoundException("WorkoutType not found with id: " + request.getWorkoutTypeId()));

        if (request.getDurationMinutes() < 5 || request.getDurationMinutes() > 180) {
            throw new ValidationException("Duration must be between 5 and 180 minutes");
        }

        if (request.getWorkoutDate().isAfter(LocalDate.now())) {
            throw new ValidationException("Workout date cannot be in the future");
        }

        WorkoutSession session = new WorkoutSession();
        session.setUser(user);
        session.setWorkoutTypeId(workoutType);
        session.setDurationMinutes(request.getDurationMinutes());
        session.setIntensity(IntensityLevel.valueOf(request.getIntensity().toUpperCase()));
        session.setWorkoutDate(request.getWorkoutDate());

        Double calories = workoutType.getMetValue() * user.getWeight() * (request.getDurationMinutes() / 60.0);
        session.setCaloriesBurned(calories);

        WorkoutSession saved = workoutSessionRepository.save(session);
        log.info("Workout session created with id: {}, calories burned: {}", saved.getId(), calories);

        return toResponse(saved);
    }

    @Override
    public WorkoutSessionResponse getSession(Long id) {
        log.info("Fetching workout session with id: {}", id);

        WorkoutSession session = workoutSessionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("WorkoutSession not found with id: " + id));

        return toResponse(session);
    }

    @Override
    public List<WorkoutSessionResponse> getSessionsByUser(Long userId) {
        log.info("Fetching all workout sessions for user id: {}", userId);

        if (!userRepository.existsById(userId)) {
            throw new ResourceNotFoundException("User not found with id: " + userId);
        }

        List<WorkoutSession> sessions = workoutSessionRepository.findByUserId(userId);
        List<WorkoutSessionResponse> responses = new ArrayList<>();

        for (WorkoutSession session : sessions) {
            responses.add(toResponse(session));
        }

        return responses;
    }

    @Override
    @Transactional
    public WorkoutSessionResponse updateSession(Long id, WorkoutSessionRequest request) {
        log.info("Updating workout session with id: {}", id);

        WorkoutSession session = workoutSessionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("WorkoutSession not found with id: " + id));

        if (request.getDurationMinutes() < 5 || request.getDurationMinutes() > 180) {
            throw new ValidationException("Duration must be between 5 and 180 minutes");
        }

        if (request.getWorkoutDate().isAfter(LocalDate.now())) {
            throw new ValidationException("Workout date cannot be in the future");
        }

        if (!request.getUserId().equals(session.getUser().getId())) {
            User user = userRepository.findById(request.getUserId())
                    .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + request.getUserId()));
            session.setUser(user);
        }

        if (!request.getWorkoutTypeId().equals(session.getWorkoutTypeId().getId())) {
            WorkoutType workoutType = workoutTypeRepository.findById(request.getWorkoutTypeId())
                    .orElseThrow(() -> new ResourceNotFoundException("WorkoutType not found with id: " + request.getWorkoutTypeId()));
            session.setWorkoutTypeId(workoutType);
        }

        session.setDurationMinutes(request.getDurationMinutes());
        session.setIntensity(IntensityLevel.valueOf(request.getIntensity().toUpperCase()));
        session.setWorkoutDate(request.getWorkoutDate());

        Double calories = session.getWorkoutTypeId().getMetValue() * session.getUser().getWeight() * (request.getDurationMinutes() / 60.0);
        session.setCaloriesBurned(calories);

        WorkoutSession updated = workoutSessionRepository.save(session);
        log.info("Workout session updated with id: {}", updated.getId());

        return toResponse(updated);
    }

    @Override
    @Transactional
    public void deleteSession(Long id) {
        log.info("Deleting workout session with id: {}", id);

        WorkoutSession session = workoutSessionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("WorkoutSession not found with id: " + id));

        workoutSessionRepository.delete(session);
        log.info("Workout session deleted with id: {}", id);
    }

    @Override
    public StatisticsResponse getStatistics(Long userId) {
        log.info("Fetching statistics for user id: {}", userId);

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));

        Long totalWorkouts = workoutSessionRepository.countByUserId(userId);
        Integer totalMinutes = workoutSessionRepository.sumDurationByUserId(userId);
        Double totalCalories = workoutSessionRepository.sumCaloriesByUserId(userId);

        StatisticsResponse response = new StatisticsResponse();
        response.setUserId(userId);
        response.setUserFullName(user.getFirstName() + " " + user.getLastName());

        if (totalWorkouts == 0) {
            response.setTotalWorkouts(0L);
            response.setTotalMinutes(0);
            response.setTotalCalories(0.0);
            response.setAverageCaloriesPerWorkout(0.0);
            return response;
        }

        response.setTotalWorkouts(totalWorkouts);
        response.setTotalMinutes(totalMinutes != null ? totalMinutes : 0);
        response.setTotalCalories(totalCalories != null ? totalCalories : 0.0);
        response.setAverageCaloriesPerWorkout(totalCalories / totalWorkouts);

        return response;
    }

    private WorkoutSessionResponse toResponse(WorkoutSession session) {
        WorkoutSessionResponse response = new WorkoutSessionResponse();
        response.setId(session.getId());
        response.setDurationMinutes(session.getDurationMinutes());
        response.setIntensityLevel(session.getIntensity() != null ? session.getIntensity().name() : null);
        response.setCaloriesBurned(session.getCaloriesBurned());
        response.setWorkoutDate(session.getWorkoutDate());
        response.setCreatedAt(session.getCreatedAt());
        response.setUpdatedAt(session.getUpdatedAt());

        if (session.getUser() != null) {
            response.setUserId(session.getUser().getId());
            response.setUserFullName(session.getUser().getFirstName() + " " + session.getUser().getLastName());
        }

        if (session.getWorkoutTypeId() != null) {
            response.setWorkoutTypeId(session.getWorkoutTypeId().getId());
            response.setWorkoutTypeName(session.getWorkoutTypeId().getName());
        }

        return response;
    }
}