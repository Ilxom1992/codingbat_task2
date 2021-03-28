package com.example.demo.service;
import com.example.demo.entity.Course;
import com.example.demo.payload.ApiResponse;
import com.example.demo.payload.CourseDto;
import com.example.demo.repository.CourseRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CourseService {
    final CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    //  CREATE
    public ApiResponse addCourse(Course course){
        courseRepository.save(course);
        return new ApiResponse("object saved",true);
    }
    //READ
    public ApiResponse getCourse(){

        return new ApiResponse("object ",true,courseRepository.findAll());
    }
    //UPDATE
    public ApiResponse editCourse(Integer id,Course course){
        Optional<Course> optionalCourse = courseRepository.findById(id);
        if (!optionalCourse.isPresent()){
            return new ApiResponse("object not found",false);
        }
        Course course1= optionalCourse.get();
        course1.setName(course.getName());
        course1.setDescription(course.getDescription());
        courseRepository.save(course1);
        return new ApiResponse("object edited",true);
    }
    //DELETE
    public ApiResponse deleteCourse( Integer id ){
        courseRepository.deleteById(id);
        return new ApiResponse("object deleted",true);
    }
    //READ BY ID
    public ApiResponse getByIdCourse( Integer id ){
courseRepository.findById(id);
        return new ApiResponse("object deleted",true);
    }


}
