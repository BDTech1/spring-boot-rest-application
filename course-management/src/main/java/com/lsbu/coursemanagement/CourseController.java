package com.lsbu.coursemanagement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/divisions")


public class CourseController {

    @Autowired
    private CourseRepository courseRepository;

    // List all unique divisions
    @GetMapping("")
    public ResponseEntity<List<String>> getAllDivisions() {
        List<Course> courses = courseRepository.findAll();
        List<String> divisions = courses.stream()
                .map(Course::getDivision)
                .distinct()
                .collect(Collectors.toList());
        if (divisions.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(divisions, HttpStatus.OK);
    }

    // List all courses by division
    @GetMapping("/{division}")
    public ResponseEntity<List<Course>> getCoursesByDivision(@PathVariable String division) {
        List<Course> courses = courseRepository.findByDivision(division);
        if (courses.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(courses, HttpStatus.OK);
    }

    // List all courses for a specified division with a result code
    @GetMapping("/{division}/courses/all")
    public ResponseEntity<Map<String, Object>> getAllCoursesByDivisionWithResultCode(@PathVariable String division) {
        List<Course> courses = courseRepository.findByDivision(division);
        Map<String, Object> response = new HashMap<>();
        if (courses.isEmpty()) {
            response.put("result", "ERROR");
            response.put("message", "No courses found for division: " + division);
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        } else {
            response.put("result", "SUCCESS");
            response.put("courses", courses);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }

    // Retrieve a specific course by ID within a specified division with a result code
    @GetMapping("/{division}/courses/{id}")
    public ResponseEntity<Map<String, Object>> getCourseByIdAndDivisionWithResultCode(@PathVariable String division, @PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();
        Optional<Course> courseOpt = Optional.ofNullable(courseRepository.findByIdAndDivision(id, division));

        if (courseOpt.isPresent()) {
            response.put("result", "SUCCESS");
            response.put("course", courseOpt.get());
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            response.put("result", "ERROR");
            response.put("message", String.format("Course with id %d not found in division: %s", id, division));
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }


    // post method used to Create a new course within a specified division
    @PostMapping("/{division}/courses")
    public ResponseEntity<Map<String, Object>> addCourseToDivision(@PathVariable String division, @RequestBody Course course) {
        Map<String, Object> response = new HashMap<>();
        try {
            // Set the division of the course from the path variable
            course.setDivision(division);
            Course savedCourse = courseRepository.save(course);
            response.put("result", "SUCCESS");
            response.put("course", savedCourse);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            response.put("result", "ERROR");
            response.put("message", "Failed to create the course in division: " + division);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    // Update an existing course within a specified division
    @PutMapping("/{division}/{id}")
    public ResponseEntity<Map<String, Object>> updateCourseInDivision(@PathVariable String division, @PathVariable Long id, @RequestBody Course updatedCourseDetails) {
        Map<String, Object> response = new HashMap<>();
        Optional<Course> courseOpt = Optional.ofNullable(courseRepository.findByIdAndDivision(id, division));

        if (courseOpt.isPresent()) {
            Course course = courseOpt.get();
            // Update course details
            course.setName(updatedCourseDetails.getName());
            course.setDuration(updatedCourseDetails.getDuration());
            course.setUndergraduate(updatedCourseDetails.getUndergraduate());
            course.setGiCode(updatedCourseDetails.getGiCode());
            course.setJdValue(updatedCourseDetails.getJdValue());
            // No need to update division as it's derived from the path variable and should remain the same

            Course updatedCourse = courseRepository.save(course);
            response.put("result", "SUCCESS");
            response.put("course", updatedCourse);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            response.put("result", "ERROR");
            response.put("message", String.format("Course with id %d not found in division: %s", id, division));
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }


    // Delete an existing course within a specified division
    @DeleteMapping("/{division}/{id}")
    public ResponseEntity<Map<String, Object>> deleteCourseFromDivision(@PathVariable String division, @PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();
        Optional<Course> courseOpt = Optional.ofNullable(courseRepository.findByIdAndDivision(id, division));

        if (courseOpt.isPresent()) {
            courseRepository.delete(courseOpt.get());
            response.put("result", "SUCCESS");
            response.put("message", String.format("Course with id %d in division: %s has been deleted", id, division));
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            response.put("result", "ERROR");
            response.put("message", String.format("Course with id %d not found in division: %s", id, division));
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

}
