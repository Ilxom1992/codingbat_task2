package com.example.demo.service;
import com.example.demo.entity.User;
import com.example.demo.payload.ApiResponse;
import com.example.demo.payload.UserDto;
import com.example.demo.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    final UserRepository userRepository;


    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    //  CREATE
    public ApiResponse addUser(UserDto userDto){
        boolean exists = userRepository.existsByUserNameAndEmail(userDto.getUserName(), userDto.getEmail());
        if (exists){
            return new ApiResponse("object exists",false);
        }
        User user=new User();
        user.setUserName(userDto.getUserName());
        user.setEmail(user.getEmail());
        user.setFirstName(userDto.getFirstName());
        user.setLastName(user.getLastName());
        userRepository.save(user);
        return new ApiResponse("object saved",true);
    }
    //READ
    public ApiResponse getAllUser(){
        return new ApiResponse("object ",true,userRepository.findAll());
    }
    //UPDATE
    public ApiResponse edit(Integer id,UserDto userDto ){
        boolean userNameAndIdNot = userRepository.existsByUserNameAndIdNot(userDto.getUserName(), id);
        if (userNameAndIdNot){
            return new ApiResponse("UserName exists",false);
        }
        boolean emailAndIdNot = userRepository.existsByEmailAndIdNot(userDto.getEmail(), id);
        if (emailAndIdNot){
            return new ApiResponse("Email exists",false);
        }
        Optional<User> optionalUser = userRepository.findById(id);
        if (!optionalUser.isPresent()){
            return new ApiResponse("User not found",false);
        }
        User user = optionalUser.get();
        user.setUserName(userDto.getUserName());
        user.setEmail(user.getEmail());
        user.setFirstName(userDto.getFirstName());
        user.setLastName(user.getLastName());
        userRepository.save(user);

        return new ApiResponse("object edited",true);
    }
    //DELETE
    public ApiResponse delete( Integer id ){
        Optional<User> optionalUser = userRepository.findById(id);
        if (!optionalUser.isPresent()) {
            return new ApiResponse("User not found",false);
        }
        userRepository.deleteById(id);
        return new ApiResponse("object deleted",true);
    }
    //READ BY ID
    public ApiResponse getById( Integer id ){
        Optional<User> optionalUser = userRepository.findById(id);
        return new ApiResponse("object deleted",true,optionalUser.get() );
    }

}
