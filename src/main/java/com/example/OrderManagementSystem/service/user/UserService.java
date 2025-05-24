package com.example.OrderManagementSystem.service.user;

import com.example.OrderManagementSystem.dto.userdto.request.CreateUser;
import com.example.OrderManagementSystem.dto.userdto.request.UpdateUserRequest;
import com.example.OrderManagementSystem.dto.userdto.response.UserResponse;
import com.example.OrderManagementSystem.entity.User;
import com.example.OrderManagementSystem.exception.AppException;
import com.example.OrderManagementSystem.exception.ErrorCode;
import com.example.OrderManagementSystem.mapper.UserMappper;
import com.example.OrderManagementSystem.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class UserService {
    UserMappper userMappper;
    UserRepository userRepository;
    public UserResponse createUser (CreateUser request){
 if(userRepository.findByUsername(request.getUsername())) throw new AppException(ErrorCode.USER_EXISTED);
 var User = userMappper.toUser(request);
 return userMappper.toUserResponse(userRepository.save(User));
    }
    public List<UserResponse> getAllUser (){
       return userRepository.findAll().stream().map(userMappper::toUserResponse).toList();
    }
    public UserResponse getUserById(String id){
        return userMappper.toUserResponse(userRepository.findById(id).orElseThrow(()->new  AppException(ErrorCode.USER_NOT_EXISTED)));
    }
    public UserResponse update (String id, UpdateUserRequest request){
      User user= userRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));
        userMappper.toUpdateUser(user,request);
        return userMappper.toUserResponse(userRepository.save(user));
    }
}

