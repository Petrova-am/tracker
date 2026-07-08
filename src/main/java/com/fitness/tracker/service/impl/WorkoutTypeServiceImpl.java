package com.fitness.tracker.service.impl;

import com.fitness.tracker.dto.request.WorkoutTypeRequest;
import com.fitness.tracker.dto.response.WorkoutTypeResponse;
import com.fitness.tracker.entity.WorkoutType;
import com.fitness.tracker.exception.ResourceNotFoundException;
import com.fitness.tracker.exception.ValidationException;
import com.fitness.tracker.mapper.WorkoutTypeMapper;
import com.fitness.tracker.repository.WorkoutTypeRepository;
import com.fitness.tracker.service.WorkoutTypeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class WorkoutTypeServiceImpl implements WorkoutTypeService {

    private final WorkoutTypeRepository workoutTypeRepository;
    private final WorkoutTypeMapper workoutTypeMapper;

    @Override
    public WorkoutTypeResponse createWorkoutType(WorkoutTypeRequest request) {
        log.info("Creating workout type: {}", request.getName());

        if (workoutTypeRepository.existsByName(request.getName())) {
            throw new ValidationException("Workout type with name '" + request.getName() + "' already exists");
        }

        if (request.getMetValue() <= 0) {
            throw new ValidationException("MET значение должно быть больше 0");
        }

        WorkoutType workoutType = workoutTypeMapper.toEntity(request);
        WorkoutType saved = workoutTypeRepository.save(workoutType);
        log.info("Workout type created with id: {}", saved.getId());

        return workoutTypeMapper.toResponse(saved);
    }

    @Override
    public WorkoutTypeResponse getWorkoutType(Long id) {
        log.info("Fetching workout type with id: {}", id);

        WorkoutType workoutType = workoutTypeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("WorkoutType not found with id: " + id));

        return workoutTypeMapper.toResponse(workoutType);
    }

    @Override
    public List<WorkoutTypeResponse> getAllWorkoutTypes() {
        log.info("Fetching all workout types");

        List<WorkoutType> workoutTypes = workoutTypeRepository.findAll();
        return workoutTypes.stream()
                .map(workoutTypeMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public WorkoutTypeResponse updateWorkoutType(Long id, WorkoutTypeRequest request) {
        log.info("Updating workout type with id: {}", id);

        WorkoutType workoutType = workoutTypeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("WorkoutType not found with id: " + id));

        if (!request.getName().equals(workoutType.getName()) &&
                workoutTypeRepository.existsByName(request.getName())) {
            throw new ValidationException("Тип тренировки с именем'" + request.getName() + "' уже существует");
        }

        if (request.getMetValue() <= 0) {
            throw new ValidationException("MET значение должно быть больше 0");
        }

        workoutTypeMapper.updateEntity(request, workoutType);
        WorkoutType saved = workoutTypeRepository.save(workoutType);
        log.info("Workout type updated with id: {}", saved.getId());

        return workoutTypeMapper.toResponse(saved);
    }

    @Override
    public void deleteWorkoutType(Long id) {
        log.info("Deleting workout type with id: {}", id);

        WorkoutType workoutType = workoutTypeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("WorkoutType not found with id: " + id));

        workoutTypeRepository.delete(workoutType);
        log.info("Workout type deleted with id: {}", id);
    }
}