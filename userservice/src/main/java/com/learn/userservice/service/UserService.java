package com.learn.userservice.service;

import com.learn.userservice.exception.UserAlreadyExistsException;
import com.learn.userservice.exception.UserNotFoundException;
import com.learn.userservice.model.User;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


public interface UserService {

    public User saveDetails(String user, MultipartFile file) throws IOException, UserAlreadyExistsException;

    public User updateDetails(String user, MultipartFile file) throws IOException, UserNotFoundException;
}
