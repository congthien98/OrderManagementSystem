package com.example.OrderManagementSystem.controller;

import com.example.OrderManagementSystem.dto.userdto.ApiResponse;
import com.example.OrderManagementSystem.dto.userdto.request.CreateUser;
import com.example.OrderManagementSystem.dto.userdto.request.UpdateUserRequest;
import com.example.OrderManagementSystem.dto.userdto.response.UserResponse;
import com.example.OrderManagementSystem.mapper.UserMappper;
import com.example.OrderManagementSystem.repository.UserRepository;
import com.example.OrderManagementSystem.service.user.UserService;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/users")
@RestController
@RequiredArgsConstructor
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    UserRepository userRepository;
    @Autowired
    UserMappper userMappper;

    @PostMapping
    ApiResponse<UserResponse> createUser(CreateUser createUser) {
        return ApiResponse.<UserResponse>builder()
                .result(userService.createUser(createUser))
                .build();
    }

    @GetMapping("/{userId}")
    ApiResponse<UserResponse> getUser(@PathVariable("userId") String userId) {
        return ApiResponse.<UserResponse>builder()
                .result(userService.getUserById(userId))
                .build();
    }
    @GetMapping
    ApiResponse<List<UserResponse>>getAllUser(){
        return ApiResponse.<List<UserResponse>>builder()
                .result(userService.getAllUser())
                .build();
}
    @PutMapping("/{userId}")
    ApiResponse<UserResponse> updateUser(@PathVariable String userId, @RequestBody UpdateUserRequest request) {
        return ApiResponse.<UserResponse>builder()
                .result(userService.update(userId, request))
                .build();
    }

}