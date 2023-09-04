package com.example.course;

import com.example.course.enums.Category;
import com.example.course.model.Course;
import com.example.course.model.Lesson;
import com.example.course.repository.CourseRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CourseApplication {

	public static void main(String[] args) {
		SpringApplication.run(CourseApplication.class, args);
	}

	@Bean
	CommandLineRunner initDataBase(CourseRepository courseRepository){
		return args -> {
			courseRepository.deleteAll();
			Course c = new Course();
			c.setName("Angular com Spring");
			c.setCategory(Category.FRONT_END);

			Lesson lesson = new Lesson();
			lesson.setName("Introdução");
			lesson.setYoutubeUrl("https://youtube.com");
			lesson.setCourse(c);
			c.getLessons().add(lesson );

			Lesson lesson1 = new Lesson();
			lesson1.setName("Angular");
			lesson1.setYoutubeUrl("https://youtube.com/v2");
			lesson1.setCourse(c);

			c.getLessons().add(lesson1 );
			courseRepository.save(c);
		};
	}

}
