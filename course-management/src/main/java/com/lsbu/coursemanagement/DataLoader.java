package com.lsbu.coursemanagement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataLoader {

    @Autowired
    private CourseRepository courseRepository;

    @Bean
    CommandLineRunner initDatabase() {
        return args -> {
            // Adding initial course data to the database
            courseRepository.save(new Course("Introduction to Programming", 120L, true, true, 9.0, "Computer Science"));
            courseRepository.save(new Course("Data Structures", 100L, true, true, 8.5, "Computer Science"));

            courseRepository.save(new Course("Principles of Marketing", 85L, true, false, 7.0, "Business"));
            courseRepository.save(new Course("Organizational Behavior", 90L, true, false, 7.5, "Business"));

            // New divisions and courses
            courseRepository.save(new Course("Constitutional Law", 60L, true, true, 8.0, "Law"));
            courseRepository.save(new Course("Criminal Law", 50L, true, false, 7.5, "Law"));

            courseRepository.save(new Course("Anatomy and Physiology", 120L, true, true, 8.5, "Medicine"));
            courseRepository.save(new Course("Biochemistry", 100L, true, false, 8.0, "Medicine"));

            courseRepository.save(new Course("World History", 80L, true, true, 7.0, "Humanities"));
            courseRepository.save(new Course("Philosophy", 75L, true, false, 7.5, "Humanities"));

        };
    }
}

