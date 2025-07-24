package com.spring.security.configuration;

import com.spring.security.entity.UserD;
import com.spring.security.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class ConfigService implements UserDetailsService {

    private final UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserD userD = userRepo.findByUserName(username)
                .orElseThrow(() -> new UsernameNotFoundException("User Name Not found"));

        return new User(userD.getUserName(), userD.getPassword(), Collections.emptyList());
    }
}
