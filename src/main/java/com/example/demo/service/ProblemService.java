package com.example.demo.service;
import com.example.demo.entity.Problem;
import com.example.demo.payload.ApiResponse;
import com.example.demo.payload.ProblemDto;
import com.example.demo.repository.ProblemRepository;
import com.example.demo.repository.SectionRepository;
import org.springframework.stereotype.Service;

@Service
public class ProblemService {
    final ProblemRepository problemRepository;
    final SectionRepository sectionRepository;

    public ProblemService(ProblemRepository problemRepository, SectionRepository sectionRepository) {
        this.problemRepository = problemRepository;
        this.sectionRepository = sectionRepository;
    }

    //  CREATE
    public ApiResponse add(ProblemDto problemDto){
        Problem problem=new Problem();
        problem.setTitle(problemDto.getTitle());
        problem.setBody(problem.getBody());
        problem.setProblem(problem.getBody());
        problem.setSolution(problem.getSolution());
        problem.setSection(sectionRepository.findById(problemDto.getSectionId()).get());
        return new ApiResponse("object saved",true);
    }
    //READ
    public ApiResponse get( ){

        return new ApiResponse("object ",true,problemRepository.findAll() );
    }
    //UPDATE
    public ApiResponse edit(Integer id,ProblemDto problemDto ){
        Problem problem=problemRepository.findById(id).get();
        problem.setTitle(problemDto.getTitle());
        problem.setBody(problem.getBody());
        problem.setProblem(problem.getBody());
        problem.setSolution(problem.getSolution());
        problem.setSection(sectionRepository.findById(problemDto.getSectionId()).get());
        return new ApiResponse("object edited",true);
    }
    //DELETE
    public ApiResponse delete( Integer id ){
        problemRepository.deleteById(id);
        return new ApiResponse("object deleted",true);
    }
    //READ BY ID
    public ApiResponse getById( Integer id ){
        return new ApiResponse("object deleted",true, problemRepository.findById(id));
    }



}
