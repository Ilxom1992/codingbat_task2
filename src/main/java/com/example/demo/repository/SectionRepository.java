package com.example.demo.repository;

import com.example.demo.entity.Section;
import com.example.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SectionRepository extends JpaRepository<Section,Integer> {

}
