package com.spring.security.service;


import com.spring.security.dto.request.UserRequest;
import com.spring.security.dto.response.UserResponse;
import com.spring.security.entity.UserD;
import com.spring.security.mapper.Mapper;
import com.spring.security.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepo userRepo;
    private final Mapper mapper;
    private final PasswordEncoder  passwordEncoder;

    public UserResponse createUser(UserRequest userRequest){
        UserD userD = mapper.toEntity(userRequest);
        userD.setPassword(passwordEncoder.encode(userD.getPassword()));
        return mapper.toUserResponseDto(userRepo.save(userD));
    }

    public UserResponse updateUser(UserRequest userRequest, Long id){
        UserD existingUserD = userRepo.findById(id).orElseThrow(()-> new RuntimeException("User not found"));

        Optional.ofNullable(userRequest.getName()).ifPresent(existingUserD::setName);
        Optional.ofNullable(userRequest.getEmail()).ifPresent(existingUserD::setEmail);
        Optional.ofNullable(userRequest.getPhone()).ifPresent(existingUserD::setPhone);
        Optional.ofNullable(userRequest.getAddress()).ifPresent(existingUserD::setAddress);
        Optional.ofNullable(userRequest.getPin()).ifPresent(existingUserD::setPin);

        UserD userD =userRepo.save(existingUserD);
        return mapper.toUserResponseDto(userD);
    }

    public UserResponse getUser(Long id){
        UserD userD =userRepo.findById(id).orElseThrow(()-> new RuntimeException("User not Found"));
        return mapper.toUserResponseDto(userD);
    }

    public List<UserResponse> findAllUser(){
        List<UserResponse> userResponses = userRepo.findAll().stream().map(mapper::toUserResponseDto).toList();
        return userResponses;
    }

    public void deleteUser(Long id){
        UserD userD = userRepo.findById(id).orElseThrow(()-> new RuntimeException("User Not Found"));
        userRepo.delete(userD);
    }


}
