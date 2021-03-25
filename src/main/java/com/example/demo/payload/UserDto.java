package com.example.demo.payload;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class UserDto {
    @NotNull(message = "FullName Bo'sh bo'lmasligi kerak")
    private String userName;
    @NotNull(message = "FullName Bo'sh bo'lmasligi kerak")
    private String firstName;
    @NotNull(message = "FullName Bo'sh bo'lmasligi kerak")
    private String lastName;
    @NotNull(message = "FullName Bo'sh bo'lmasligi kerak")
    private String email;
}
