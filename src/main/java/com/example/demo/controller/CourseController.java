package com.example.demo.controller;
import com.example.demo.entity.Course;
import com.example.demo.payload.ApiResponse;
import com.example.demo.service.CourseService;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;
@RestController
@RequestMapping(value = "/api/course")
public class CourseController {
    final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    //  CREATE
    @PostMapping
    public HttpEntity<ApiResponse> add(@Valid @RequestBody Course course){
        return ResponseEntity.ok(courseService.addCourse(course));
    }
    //READ
    @GetMapping
    public HttpEntity<ApiResponse> get(){
        return ResponseEntity.ok(courseService.getCourse());
    }
    //UPDATE
    @PutMapping(value = "/{id}")
    public HttpEntity<ApiResponse> edit(@PathVariable Integer id, @Valid @RequestBody Course course){
        return ResponseEntity.ok(courseService.editCourse(id,course));
    }
    //DELETE
    @DeleteMapping(value = "/{id}")
    public HttpEntity<ApiResponse> delete(@PathVariable Integer id){
        return ResponseEntity.ok(courseService.deleteCourse(id));
    }
    //READ BY ID
    @GetMapping(value = "/{id}")
    public HttpEntity<ApiResponse> getById(@PathVariable Integer id){
        return ResponseEntity.ok(courseService.getByIdCourse(id));
    }
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }

}
