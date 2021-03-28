package com.example.demo.controller;

import com.example.demo.payload.ApiResponse;
import com.example.demo.payload.UserPracticeDto;
import com.example.demo.service.UserPracticeService;
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
@RequestMapping(value = "/api/userPractice")
public class UserPracticeController {
    final UserPracticeService userPracticeService;

    public UserPracticeController(UserPracticeService userPracticeService) {
        this.userPracticeService = userPracticeService;
    }
    //  CREATE
    @PostMapping
    public HttpEntity<ApiResponse> add(@Valid @RequestBody UserPracticeDto userPracticeDto){
        return ResponseEntity.status( HttpStatus.CONFLICT).body(userPracticeService.add(userPracticeDto));
    }
    //READ
    @GetMapping
    public HttpEntity<ApiResponse> get(){
        return ResponseEntity.ok(userPracticeService.get());
    }
    //UPDATE
    @PutMapping(value = "/{id}")
    public HttpEntity<ApiResponse> edit(@PathVariable Integer id,@Valid @RequestBody UserPracticeDto userPracticeDto){
        return ResponseEntity.ok().body(userPracticeService.edit(id,userPracticeDto));
    }
    //DELETE
    @DeleteMapping(value = "/{id}")
    public HttpEntity<ApiResponse> delete(@PathVariable Integer id){
        return ResponseEntity.ok(userPracticeService.delete(id));
    }
    //READ BY ID
    @GetMapping(value = "/{id}")
    public HttpEntity<ApiResponse> getById(@PathVariable Integer id){
        return ResponseEntity.ok(userPracticeService.getById(id));
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
