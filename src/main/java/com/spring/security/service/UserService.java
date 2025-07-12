package com.spring.security.service;


import com.spring.security.dto.request.UserRequest;
import com.spring.security.dto.response.UserResponse;
import com.spring.security.entity.User;
import com.spring.security.mapper.Mapper;
import com.spring.security.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepo userRepo;
    private final Mapper mapper;

    public UserResponse createUser(UserRequest userRequest){
        User user = mapper.toEntity(userRequest);
        return mapper.toUserResponseDto(userRepo.save(user));
    }

    public UserResponse updateUser(UserRequest userRequest, Long id){
        User existingUser = userRepo.findById(id).orElseThrow(()-> new RuntimeException("User not found"));

        Optional.ofNullable(userRequest.getName()).ifPresent(existingUser::setName);
        Optional.ofNullable(userRequest.getEmail()).ifPresent(existingUser::setEmail);
        Optional.ofNullable(userRequest.getPhone()).ifPresent(existingUser::setPhone);
        Optional.ofNullable(userRequest.getAddress()).ifPresent(existingUser::setAddress);
        Optional.ofNullable(userRequest.getPin()).ifPresent(existingUser::setPin);

        User user=userRepo.save(existingUser);
        return mapper.toUserResponseDto(user);
    }

    public UserResponse getUser(Long id){
        User user=userRepo.findById(id).orElseThrow(()-> new RuntimeException("User not Found"));
        return mapper.toUserResponseDto(user);
    }

    public List<UserResponse> findAllUser(){
        List<UserResponse> userResponses = userRepo.findAll().stream().map(mapper::toUserResponseDto).toList();
        return userResponses;
    }

    public void deleteUser(Long id){
        User user = userRepo.findById(id).orElseThrow(()-> new RuntimeException("User Not Found"));
        userRepo.delete(user);
    }


}
