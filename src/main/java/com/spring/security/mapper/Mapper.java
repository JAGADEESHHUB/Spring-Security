package com.spring.security.mapper;

import com.spring.security.dto.request.UserRequest;
import com.spring.security.dto.response.UserResponse;
import com.spring.security.entity.UserD;
import org.springframework.stereotype.Component;

@Component
public class Mapper {

    public UserD toEntity(UserRequest userRequest){
        return UserD.builder()
                .name(userRequest.getName())
                .email(userRequest.getEmail())
                .address(userRequest.getAddress())
                .phone(userRequest.getPhone())
                .pin(userRequest.getPin())
                .userName(userRequest.getUserName())
                .password(userRequest.getPassword())
                .build();
    }

    public UserResponse toUserResponseDto(UserD userD){
        return UserResponse.builder()
                .id(userD.getId())
                .name(userD.getName())
                .email(userD.getEmail())
                .address(userD.getAddress())
                .phone(userD.getPhone())
                .build();
    }

}
