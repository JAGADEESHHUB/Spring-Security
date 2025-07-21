package com.spring.security.configuration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Slf4j
@Configuration
@EnableWebSecurity
public class Config {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(HttpMethod.POST,"/user").permitAll()// here we are telling that no need to restrict the api with /user specifically(POST)
                                                                                  // and this need to be said first since we are authenticating all APIs starting /user
                        .requestMatchers("/user/**").authenticated() //telling that to secure all the pages with the API-URLs
                        .requestMatchers("/home").permitAll() //telling that to don't secure all the pages with the API-URLs
                .requestMatchers("/home/login/successfully").authenticated() //telling that to secure all the pages with the API-URLs
                .anyRequest().permitAll()//telling that to don't secure rest API-URLs of above
        )
                .formLogin(form-> form.permitAll().defaultSuccessUrl("/home/login/successfully"))
                .csrf(csrf-> csrf.disable()); //since we dont need csrf function for APIs


        return http.build();
    }


    @Bean //in memory User
    public UserDetailsService userDetailsService(PasswordEncoder passwordEncoder){
        UserDetails userDetails = User.withUsername("jagadeesh")
                .password(passwordEncoder.encode("2004"))
                .roles("USER")
                .build();
        return new InMemoryUserDetailsManager(userDetails);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }



}
