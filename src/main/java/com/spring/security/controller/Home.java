package com.spring.security.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/home")
public class Home {

    @GetMapping
    public ResponseEntity<String> home() {
        return ResponseEntity.ok("Home");
    }

    @GetMapping("/login/successfully")
    public ResponseEntity<String> loginSuccessfully() {
        return ResponseEntity.ok("Login successfully");
    }

}
