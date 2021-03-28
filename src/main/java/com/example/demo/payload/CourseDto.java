package com.example.demo.payload;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class CourseDto {
    @NotNull(message = "Name Bo'sh bo'lmasligi kerak")
    private String name;

    private  String description;
}
