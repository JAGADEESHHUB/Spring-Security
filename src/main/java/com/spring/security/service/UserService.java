package com.spring.security.service;


import com.spring.security.dto.request.UserRequest;
import com.spring.security.dto.response.UserResponse;
import com.spring.security.entity.User;
import com.spring.security.mapper.Mapper;
import com.spring.security.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepo userRepo;
    private final Mapper mapper;

    public UserResponse createUser(UserRequest userRequest){
        User user = mapper.toEntity(userRequest);
        return mapper.toUserResponseDto(userRepo.save(user));
    }


}
