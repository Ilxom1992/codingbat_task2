package com.example.demo.payload;

import lombok.Data;

import java.util.Date;

@Data
public class UserPracticeDto {
    private Integer userId;
    private Integer problemId;
    private String userSolition;
    private Integer score;
    private Date date;
}
