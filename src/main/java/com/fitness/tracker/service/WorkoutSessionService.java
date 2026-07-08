package com.fitness.tracker.service;

import com.fitness.tracker.dto.request.WorkoutSessionRequest;
import com.fitness.tracker.dto.response.StatisticsResponse;
import com.fitness.tracker.dto.response.WorkoutSessionResponse;

import java.util.List;

public interface WorkoutSessionService {

    WorkoutSessionResponse createSession(WorkoutSessionRequest request);

    WorkoutSessionResponse getSession(Long id);

    List<WorkoutSessionResponse> getSessionsByUser(Long userId);

    WorkoutSessionResponse updateSession(Long id, WorkoutSessionRequest request);

    void deleteSession(Long id);

    StatisticsResponse getStatistics(Long userId);
}