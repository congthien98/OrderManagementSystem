package com.example.OrderManagementSystem.mapper;

import com.example.OrderManagementSystem.dto.userdto.request.CreateUser;
import com.example.OrderManagementSystem.dto.userdto.request.UpdateUserRequest;
import com.example.OrderManagementSystem.dto.userdto.response.UserResponse;
import com.example.OrderManagementSystem.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;


@Mapper(componentModel = "spring")
public interface UserMappper {
    User toUser (CreateUser request);
    UserResponse toUserResponse (User user);
    void toUpdateUser(@MappingTarget User user, UpdateUserRequest request);
}
