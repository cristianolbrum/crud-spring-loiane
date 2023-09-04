package com.example.course.dto.mapper;

import com.example.course.dto.CourseDTO;
import com.example.course.enums.Category;
import com.example.course.model.Course;
import org.springframework.stereotype.Component;

@Component
public class CourseMapper {

    public CourseDTO toDTO(Course course){
        if (course == null) {
            return null;
        }
        return new CourseDTO(course.getId(), course.getName(), course.getCategory().getValue());
    }

    public Course toEntity(CourseDTO courseDTO){
        if (courseDTO == null){
            return null;
        }
        Course course = new Course();
        if(courseDTO.id() != null) {
            course.setId(courseDTO.id());
        }
        course.setName(courseDTO.name());
        course.setCategory(convertToCategory(courseDTO.category()));
        return course;
    }

    public Category convertToCategory(String value){
        if(value == null){
            return null;
        }
        return switch (value){
            case "Front-end" -> Category.FRONT_END;
            case "Back-end" -> Category.BACK_END;
            default -> throw new IllegalArgumentException("Categoria Invalida "+ value);
        };
    }
}
