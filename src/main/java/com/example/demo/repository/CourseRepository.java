package com.example.demo.repository;

import com.example.demo.entity.Course;
import com.example.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course,Integer> {

}
