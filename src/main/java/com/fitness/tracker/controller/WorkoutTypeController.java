package com.fitness.tracker.controller;

import com.fitness.tracker.dto.request.WorkoutTypeRequest;
import com.fitness.tracker.dto.response.WorkoutTypeResponse;
import com.fitness.tracker.service.WorkoutTypeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/workout-types")
@RequiredArgsConstructor
public class WorkoutTypeController {

    private final WorkoutTypeService workoutTypeService;

    @PostMapping
    public ResponseEntity<WorkoutTypeResponse> createWorkoutType(@Valid @RequestBody WorkoutTypeRequest request) {
        WorkoutTypeResponse response = workoutTypeService.createWorkoutType(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<WorkoutTypeResponse> getWorkoutType(@PathVariable Long id) {
        WorkoutTypeResponse response = workoutTypeService.getWorkoutType(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<WorkoutTypeResponse>> getAllWorkoutTypes() {
        List<WorkoutTypeResponse> responses = workoutTypeService.getAllWorkoutTypes();
        return ResponseEntity.ok(responses);
    }

    @PutMapping("/{id}")
    public ResponseEntity<WorkoutTypeResponse> updateWorkoutType(@PathVariable Long id, @Valid @RequestBody WorkoutTypeRequest request) {
        WorkoutTypeResponse response = workoutTypeService.updateWorkoutType(id, request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWorkoutType(@PathVariable Long id) {
        workoutTypeService.deleteWorkoutType(id);
        return ResponseEntity.noContent().build();
    }
}