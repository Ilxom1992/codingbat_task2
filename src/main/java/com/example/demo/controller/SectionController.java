package com.example.demo.controller;

import com.example.demo.payload.ApiResponse;
import com.example.demo.payload.SectionDto;
import com.example.demo.service.SectionService;
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
@RequestMapping("/api/section")
public class SectionController {
  final SectionService sectionService;

    public SectionController(SectionService sectionService) {
        this.sectionService = sectionService;
    }

    //  CREATE
    @PostMapping
    public HttpEntity<ApiResponse> add(@Valid @RequestBody SectionDto sectionDto){
        return ResponseEntity.status(HttpStatus.CONFLICT).body(sectionService.addSection(sectionDto));
    }
    //READ
    @GetMapping
    public HttpEntity<ApiResponse> get(){
        return ResponseEntity.ok(sectionService.get());
    }
    //UPDATE
    @PutMapping(value = "/id")
    public HttpEntity<ApiResponse> edit(@PathVariable Integer id, @Valid @RequestBody SectionDto sectionDto ){
        return ResponseEntity.ok().body(sectionService.edit(id,sectionDto));
    }
    //DELETE
    @DeleteMapping(value = "/{id}")
    public HttpEntity<ApiResponse> delete(@PathVariable Integer id){
        return ResponseEntity.ok(sectionService.delete(id));
    }
    //READ BY ID
    @GetMapping(value = "/{id}")
    public HttpEntity<ApiResponse> getById(@PathVariable Integer id){
        return ResponseEntity.ok(sectionService.getById(id));
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
