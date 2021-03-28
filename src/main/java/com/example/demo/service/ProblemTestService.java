package com.example.demo.service;
import com.example.demo.entity.ProblemTest;
import com.example.demo.payload.ApiResponse;
import com.example.demo.payload.ProblemTestDto;
import com.example.demo.repository.ProblemRepository;
import com.example.demo.repository.ProblemTestRepository;
import org.springframework.stereotype.Service;

@Service
public class ProblemTestService {
    final ProblemTestRepository problemTestRepository;
    final ProblemRepository problemRepository;

    public ProblemTestService(ProblemTestRepository problemTestRepository, ProblemRepository problemRepository) {
        this.problemTestRepository = problemTestRepository;
        this.problemRepository = problemRepository;
    }

    //  CREATE
    public ApiResponse add(ProblemTestDto problemTestDto){
        ProblemTest problemTest=new ProblemTest();
        problemTest.setArguments(problemTestDto.getArguments());
        problemTest.setResult(problemTestDto.getResult());
        problemTest.setProblem(problemRepository.findById(problemTestDto.getProblemId()).get());
        problemTestRepository.save(problemTest);
        return new ApiResponse("object saved",true);
    }
    //READ
    public ApiResponse get( ){
        return new ApiResponse("object ",true,problemTestRepository.findAll());
    }
    //UPDATE
    public ApiResponse edit(Integer id,ProblemTestDto problemTestDto ){
ProblemTest problemTest=problemTestRepository.findById(id).get();
        problemTest.setArguments(problemTestDto.getArguments());
        problemTest.setResult(problemTestDto.getResult());
        problemTest.setProblem(problemRepository.findById(problemTestDto.getProblemId()).get());
        problemTestRepository.save(problemTest);
        return new ApiResponse("object edited",true);
    }
    //DELETE
    public ApiResponse delete( Integer id ){
        problemTestRepository.deleteById(id);
        return new ApiResponse("object deleted",true);
    }
    //READ BY ID
    public ApiResponse getById( Integer id ){
problemTestRepository.findById(id);
        return new ApiResponse("object deleted",true );
    }
}
