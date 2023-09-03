package com.example.course.service;

import com.example.course.exception.RecordNotFoundException;
import com.example.course.model.Course;
import com.example.course.repository.CourseRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Service
public class CourseService {

    private final CourseRepository courseRepository;

    public List<Course> list() {
        return courseRepository.findAll();
    }

    public Course findById(@PathVariable @NotNull @Positive Long id) {
        return courseRepository.findById(id).orElseThrow(() -> new RecordNotFoundException(id));

    }

    public Course create(@Valid Course course) {
        return courseRepository.save(course);
    }

    public Course update(@Valid @NotNull @Positive Long id, Course course) {
        return courseRepository.findById(id)
                .map(recordFound -> {
                    recordFound.setName(course.getName());
                    recordFound.setCategory(course.getCategory());
                    Course updated = courseRepository.save(recordFound);
                    return updated;
                }).orElseThrow(() -> new RecordNotFoundException(id));
    }

    public void delete(@NotNull Long id) {
        courseRepository.delete(courseRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException(id)));
}

    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

}
