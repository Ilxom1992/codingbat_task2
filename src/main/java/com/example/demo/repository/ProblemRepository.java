package com.example.demo.repository;

import com.example.demo.entity.Problem;
import com.example.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProblemRepository extends JpaRepository<Problem,Integer> {

}
