package com.fitness.tracker.controller;

import com.fitness.tracker.dto.request.WorkoutSessionRequest;
import com.fitness.tracker.dto.response.StatisticsResponse;
import com.fitness.tracker.dto.response.WorkoutSessionResponse;
import com.fitness.tracker.service.WorkoutSessionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sessions")
@RequiredArgsConstructor
public class WorkoutSessionController {

    private final WorkoutSessionService workoutSessionService;

    @PostMapping
    public ResponseEntity<WorkoutSessionResponse> createSession(@Valid @RequestBody WorkoutSessionRequest request) {
        WorkoutSessionResponse response = workoutSessionService.createSession(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<WorkoutSessionResponse> getSession(@PathVariable Long id) {
        WorkoutSessionResponse response = workoutSessionService.getSession(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<WorkoutSessionResponse>> getSessionsByUser(@PathVariable Long userId) {
        List<WorkoutSessionResponse> responses = workoutSessionService.getSessionsByUser(userId);
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/statistics")
    public ResponseEntity<StatisticsResponse> getStatistics(@RequestParam Long userId) {
        StatisticsResponse response = workoutSessionService.getStatistics(userId);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<WorkoutSessionResponse> updateSession(
            @PathVariable Long id,
            @Valid @RequestBody WorkoutSessionRequest request) {
        WorkoutSessionResponse response = workoutSessionService.updateSession(id, request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSession(@PathVariable Long id) {
        workoutSessionService.deleteSession(id);
        return ResponseEntity.noContent().build();
    }
}