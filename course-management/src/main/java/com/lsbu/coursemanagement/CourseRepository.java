package com.lsbu.coursemanagement;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Long> {
    List<Course> findByDivision(String division);

    // Find a single course within a specific division by its ID
    Course findByIdAndDivision(Long id, String division);


}