package com.example.demo.controller;
import com.example.demo.payload.ApiResponse;
import com.example.demo.payload.UserDto;
import com.example.demo.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;
@RestController
@RequestMapping(value = "/app/coding/user")
public class UserController {

    final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }
    //  CREATE
    @PostMapping
    public ResponseEntity<ApiResponse> add(@Valid @RequestBody UserDto userDto){
        return ResponseEntity.ok(userService.addUser(userDto));
    }
    //READ
    @GetMapping
    public ResponseEntity<ApiResponse> get(){
        return ResponseEntity.ok(userService.getAllUser());
    }
    //UPDATE
    @PutMapping(value = "/{id}")
    public ResponseEntity<ApiResponse> edit(@Valid @RequestBody UserDto userDto,@PathVariable Integer id){
        return ResponseEntity.ok(userService.edit(id,userDto));
    }
    //DELETE
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<ApiResponse> delete(@PathVariable Integer id){
        return ResponseEntity.ok(userService.delete(id));
    }
    //READ BY ID
    @GetMapping(value = "/{id}")
    public ResponseEntity<ApiResponse> getById(@PathVariable Integer id){
        return ResponseEntity.ok(userService.getById(id));
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
