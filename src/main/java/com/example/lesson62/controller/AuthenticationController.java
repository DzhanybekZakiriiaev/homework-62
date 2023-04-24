package com.example.lesson62.controller;

import com.example.lesson62.dto.LoginDto;
import com.example.lesson62.dto.SignUpDto;
import com.example.lesson62.entity.User;
import com.example.lesson62.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@RequiredArgsConstructor
public class AuthenticationController {
    @Autowired
    private UserService userService;

    @PostMapping("/authorize")
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
            return ResponseEntity.badRequest().body("{\"message\": \"Username is already taken!\"}");
        }
        if (userService.checkUserFromEmail(signUpDto.getEmail()).contains(signUpDto.getEmail())) {
            return ResponseEntity.badRequest().body("{\"message\": \"Email is already taken!\"}");
        }
        User user = new User();
        user.setAccountName(signUpDto.getUsername());
        user.setEmail(signUpDto.getEmail());
        user.setPassword(new BCryptPasswordEncoder().encode(signUpDto.getPassword()));
        userService.saveToBase(user);
        return ResponseEntity.ok().body("{\"message\": \"User registered successfully\"}");
    }

    @GetMapping("/register")
    public String showSignUp(Model model){
        model.addAttribute("pageTitle", "Register");
        model.addAttribute("message", "Welcome to the registration page!");
        return "register";
    }


    @GetMapping("/my-login-page")
    public String showLogin(){
        return "login";
    }
}
