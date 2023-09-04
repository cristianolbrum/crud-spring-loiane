package com.example.course.service;

import com.example.course.dto.CourseDTO;
import com.example.course.dto.mapper.CourseMapper;
import com.example.course.exception.RecordNotFoundException;
import com.example.course.model.Course;
import com.example.course.repository.CourseRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CourseService {

    private final CourseRepository courseRepository;
    private final CourseMapper courseMapper;

    public List<CourseDTO> list() {
        return courseRepository.findAll()
                .stream().map(courseMapper::toDTO)
                .collect(Collectors.toList());
    }

    public CourseDTO findById(@PathVariable @NotNull @Positive Long id) {
        return courseRepository.findById(id).map(courseMapper::toDTO).orElseThrow(() -> new RecordNotFoundException(id));

    }

    public CourseDTO create(@Valid @NotNull CourseDTO course) {
        return courseMapper.toDTO(courseRepository.save(courseMapper.toEntity(course)));
    }

    public CourseDTO update(@Valid @NotNull @Positive Long id, CourseDTO course) {
        return courseRepository.findById(id)
                .map(recordFound -> {
                    recordFound.setName(course.name());
                    recordFound.setCategory(course.category());
                    return courseMapper.toDTO(courseRepository.save(courseMapper.toEntity(course)));
                }).orElseThrow(() -> new RecordNotFoundException(id));
    }

    public void delete(@NotNull Long id) {
        courseRepository.delete(courseRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException(id)));
}

    public CourseService(CourseRepository courseRepository, CourseMapper courseMapper) {
        this.courseRepository = courseRepository;
        this.courseMapper = courseMapper;
    }

}
