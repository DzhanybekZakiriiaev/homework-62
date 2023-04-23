package com.example.lesson62.controller;

import com.example.lesson62.dto.LoginDto;
import com.example.lesson62.dto.SignUpDto;
import com.example.lesson62.entity.User;
import com.example.lesson62.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthenticationController {
    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<Boolean> authenticateUser(@RequestBody LoginDto loginDto) {
        if (userService.login(loginDto.getUsernameOrEmail(), loginDto.getPassword())) {
            return new ResponseEntity<>(userService.login(loginDto.getUsernameOrEmail(),
                    loginDto.getPassword()), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/sign-up")
    public ResponseEntity<?> registerUser(@RequestBody SignUpDto signUpDto) {
        if (userService.checkUserFromAccountName(signUpDto.getUsername()).contains(signUpDto.getUsername())) {
            return new ResponseEntity<>("Username is already taken!", HttpStatus.BAD_REQUEST);
        }
        if (userService.checkUserFromEmail(signUpDto.getEmail()).contains(signUpDto.getEmail())) {
            return new ResponseEntity<>("Email is already taken!", HttpStatus.BAD_REQUEST);
        }
        User user = new User();
        user.setAccountName(signUpDto.getUsername());
        user.setEmail(signUpDto.getEmail());
        user.setPassword(signUpDto.getPassword());
        userService.saveToBase(user);
        return new ResponseEntity<>("User registered successfully", HttpStatus.OK);
    }

    @GetMapping("/sign-up")
    public String registerUser(){
        return "sign-up";
    }

    @GetMapping("/login")
    public String authenticateUser(){
        return "sign-up";
    }
}
