package com.example.demo.payload;
import lombok.Data;
@Data
public class ProblemDto {
    private  String title;
    private  String body;
    private  String problem;
    private  String solution;
    private  Integer sectionId;
}
