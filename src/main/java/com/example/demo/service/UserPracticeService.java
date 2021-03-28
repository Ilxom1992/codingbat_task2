package com.example.demo.service;
import com.example.demo.entity.UserPractice;
import com.example.demo.payload.ApiResponse;
import com.example.demo.payload.UserPracticeDto;
import com.example.demo.repository.ProblemRepository;
import com.example.demo.repository.UserPracticeRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserPracticeService {
    final UserPracticeRepository userPracticeRepository;
    final UserRepository userRepository;
    final ProblemRepository problemRepository;

    public UserPracticeService(UserPracticeRepository userPracticeRepository, UserRepository userRepository, ProblemRepository problemRepository) {
        this.userPracticeRepository = userPracticeRepository;
        this.userRepository = userRepository;
        this.problemRepository = problemRepository;
    }


    //  CREATE
    public ApiResponse add(UserPracticeDto userPracticeDto){
        UserPractice userPractice=new UserPractice();
        userPractice.setUsers(userRepository.findById(userPracticeDto.getUserId()).get());
        userPractice.setProblem(problemRepository.findById(userPracticeDto.getProblemId()).get());
        userPractice.setUserSolution(userPracticeDto.getUserSolition());
        userPractice.setScore(userPracticeDto.getScore());
        userPractice.setDate(userPracticeDto.getDate());
        userPracticeRepository.save(userPractice);
        return new ApiResponse("object saved",true);
    }
    //READ
    public ApiResponse get( ){

        return new ApiResponse("object ",true,userPracticeRepository.findAll());
    }
    //UPDATE
    public ApiResponse edit(Integer id,UserPracticeDto userPracticeDto ){
UserPractice userPractice= userPracticeRepository.findById(id).get();
        userPractice.setUsers(userRepository.findById(userPracticeDto.getUserId()).get());
        userPractice.setProblem(problemRepository.findById(userPracticeDto.getProblemId()).get());
        userPractice.setUserSolution(userPracticeDto.getUserSolition());
        userPractice.setScore(userPracticeDto.getScore());
        userPractice.setDate(userPracticeDto.getDate());
        userPracticeRepository.save(userPractice);
        return new ApiResponse("object edited",true);
    }
    //DELETE
    public ApiResponse delete( Integer id ){
        userPracticeRepository.deleteById(id);
        return new ApiResponse("object deleted",true);
    }
    //READ BY ID
    public ApiResponse getById( Integer id ){
userPracticeRepository.findById(id);
        return new ApiResponse("object deleted",true );
    }

}
