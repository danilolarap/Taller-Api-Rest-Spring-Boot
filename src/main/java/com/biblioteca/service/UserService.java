package com.biblioteca.service;

import com.biblioteca.dto.UserRequest;
import com.biblioteca.dto.UserResponse;

import java.util.List;

public interface UserService {

    UserResponse createUser(UserRequest request);

    UserResponse updateUser(String id, UserRequest request);

    void deleteUser(String id);

    UserResponse getUser(String id);

    List<UserResponse> getAllUsers();
}