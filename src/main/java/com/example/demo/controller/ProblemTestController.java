package com.example.demo.controller;

import com.example.demo.payload.ApiResponse;
import com.example.demo.payload.ProblemTestDto;
import com.example.demo.service.ProblemTestService;
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
@RequestMapping(value = "/api/problemTest")
public class ProblemTestController {
    final ProblemTestService problemTestService;

    public ProblemTestController(ProblemTestService problemTestService) {
        this.problemTestService = problemTestService;
    }

    //  CREATE
    @PostMapping
    public HttpEntity<ApiResponse> add(@Valid @RequestBody ProblemTestDto problemTestDto){
        return ResponseEntity.status(HttpStatus.CONFLICT).body(problemTestService.add(problemTestDto));
    }
    //READ
    @GetMapping
    public HttpEntity<ApiResponse> get(){
        return ResponseEntity.ok(problemTestService.get());
    }
    //UPDATE
    @PutMapping(value = "/{id}")
    public HttpEntity<ApiResponse> edit(@PathVariable Integer id,@Valid @RequestBody ProblemTestDto problemTestDto){
        return ResponseEntity.ok().body(problemTestService.edit(id,problemTestDto));
    }
    //DELETE
    @DeleteMapping(value = "/{id}")
    public HttpEntity<ApiResponse> delete(@PathVariable Integer id){
        return ResponseEntity.ok(problemTestService.delete(id));
    }
    //READ BY ID
    @GetMapping(value = "/{id}")
    public HttpEntity<ApiResponse> getById(@PathVariable Integer id){
        return ResponseEntity.ok(problemTestService.getById(id));
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
