package com.spring.security.mapper;

import com.spring.security.dto.request.UserRequest;
import com.spring.security.dto.response.UserResponse;
import com.spring.security.entity.User;
import org.springframework.stereotype.Component;

@Component
public class Mapper {

    public User toEntity(UserRequest userRequest){
        return User.builder()
                .name(userRequest.getName())
                .email(userRequest.getEmail())
                .address(userRequest.getAddress())
                .phone(userRequest.getPhone())
                .pin(userRequest.getPin())
                .build();
    }

    public UserResponse toUserResponseDto(User user){
        return UserResponse.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .address(user.getAddress())
                .phone(user.getPhone())
                .build();
    }

}
