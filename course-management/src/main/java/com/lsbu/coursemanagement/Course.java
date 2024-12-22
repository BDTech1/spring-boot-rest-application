package com.lsbu.coursemanagement;



import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name; // Course name
    private Long duration; // Course duration
    private Boolean undergraduate; // course is for undergraduate students
    private Boolean giCode; // Placeholder for GI code logic (true/false)
    private Double jdValue; // Placeholder for JD value
    private String division; // Division/category the course belongs to


    // Constructors
    public Course() {}

    public Course(String name, Long duration, Boolean undergraduate, Boolean giCode, Double jdValue, String division) {
        this.name = name;
        this.duration = duration;
        this.undergraduate = undergraduate;
        this.giCode = giCode;
        this.jdValue = jdValue;
        this.division = division;
    }

    // Getters and Setters


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getDuration() {
        return duration;
    }

    public void setDuration(Long duration) {
        this.duration = duration;
    }

    public Boolean getUndergraduate() {
        return undergraduate;
    }

    public void setUndergraduate(Boolean undergraduate) {
        this.undergraduate = undergraduate;
    }

    public Boolean getGiCode() {
        return giCode;
    }

    public void setGiCode(Boolean giCode) {
        this.giCode = giCode;
    }

    public Double getJdValue() {
        return jdValue;
    }

    public void setJdValue(Double jdValue) {
        this.jdValue = jdValue;
    }

    public String getDivision() {
        return division;
    }

    public void setDivision(String division) {
        this.division = division;
    }

    // Override toString method for debugging purposes
    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", duration=" + duration +
                ", undergraduate=" + undergraduate +
                ", giCode=" + giCode +
                ", jdValue=" + jdValue +
                ", division='" + division + '\'' +
                '}';
    }






}


