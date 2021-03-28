package com.example.demo.controller;

import com.example.demo.payload.ApiResponse;
import com.example.demo.payload.ProblemDto;
import com.example.demo.service.ProblemService;
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
@RequestMapping(value = "/api/problem")
public class ProblemController {
    final ProblemService problemService;

    public ProblemController(ProblemService problemService) {
        this.problemService = problemService;
    }

    //  CREATE
    @PostMapping
    public HttpEntity<ApiResponse> add(@Valid @RequestBody ProblemDto problemDto){
        return ResponseEntity.status(HttpStatus.CONFLICT).body(problemService.add(problemDto));
    }
    //READ
    @GetMapping
    public HttpEntity<ApiResponse> get(){
        return ResponseEntity.ok(problemService.get());
    }
    //UPDATE
    @PutMapping(value = "/id")
    public HttpEntity<ApiResponse> edit(@PathVariable Integer id,@Valid @RequestBody ProblemDto problemDto ){
        return ResponseEntity.ok().body(problemService.edit(id,problemDto));
    }
    //DELETE
    @DeleteMapping(value = "/{id}")
    public HttpEntity<ApiResponse> delete(@PathVariable Integer id){
        return ResponseEntity.ok(problemService.delete(id));
    }
    //READ BY ID
    @GetMapping(value = "/{id}")
    public HttpEntity<ApiResponse> getById(@PathVariable Integer id){
        return ResponseEntity.ok(problemService.getById(id));
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
