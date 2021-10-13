package com.learn.userservice.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.learn.userservice.exception.UserAlreadyExistsException;
import com.learn.userservice.exception.UserNotFoundException;
import com.learn.userservice.model.User;
import com.learn.userservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class UserServiceImpl implements UserService{

    UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User saveDetails(String user, MultipartFile file) throws IOException, UserAlreadyExistsException {
        User user1 = new User();

        try{
            ObjectMapper objectMapper = new ObjectMapper();
            user1 = objectMapper.readValue(user, User.class);
        }
        catch (IOException e){
            System.out.println("error");
        }

        if(userRepository.existsById(user1.getEmailId())) {
            throw new UserAlreadyExistsException("User Already Exists");
        }

        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        User userFile = new User(fileName,file.getContentType(),file.getBytes());
        userFile.setUsername(user1.getUsername());
        userFile.setPassword(user1.getPassword());
        userFile.setEmailId(user1.getEmailId());
        return userRepository.save(userFile);
    }

    public User updateDetails(String user, MultipartFile file) throws IOException, UserNotFoundException {
        User user1 = new User();
        try{
            ObjectMapper objectMapper = new ObjectMapper();
            user1 = objectMapper.readValue(user, User.class);
        }
        catch (IOException e){
            System.out.println("error");
        }

        if(!userRepository.existsById(user1.getEmailId())) {
            throw new UserNotFoundException("User Not Found");
        }

        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        User userFile = new User(fileName,file.getContentType(),file.getBytes());
        userFile.setUsername(user1.getUsername());
        userFile.setPassword(user1.getPassword());
        userFile.setEmailId(user1.getEmailId());
        return userRepository.save(userFile);
    }
}
