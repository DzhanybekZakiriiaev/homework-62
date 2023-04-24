package com.example.lesson62.controller;

import com.example.lesson62.dto.SearchUsersDto;
import com.example.lesson62.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequiredArgsConstructor
public class SearchUsersController {
    @Autowired
    private UserService userService;

    @PostMapping("/search")
    public ResponseEntity<?> searchUsers(@RequestBody SearchUsersDto searchUsersDto) {
        var listName = userService.getUsersFromName(searchUsersDto.getName());
        var listAccountName = userService.getUserFromAccountName(searchUsersDto.getUsername());
        var listEmail = userService.getUserFromEmail(searchUsersDto.getEmail());
        if (listName.size() != 0) {
            return new ResponseEntity<>("User found: " + listName, HttpStatus.OK);
        } else if (listAccountName.size() != 0) {
            return new ResponseEntity<>("User found: " + listAccountName, HttpStatus.OK);
        } else if (listEmail.size() != 0) {
            return new ResponseEntity<>("User found: " + listEmail, HttpStatus.OK);
        }
        return new ResponseEntity<>("User not found", HttpStatus.OK);
    }
}
