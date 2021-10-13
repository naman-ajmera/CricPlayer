package com.learn.userservice.controller;

import com.learn.userservice.exception.UserAlreadyExistsException;
import com.learn.userservice.exception.UserNotFoundException;
import com.learn.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1")
public class UserController {

    UserService service;

    @Autowired
    public UserController(UserService service){
        this.service=service;
    }

    @PostMapping(value="/details" , consumes = {MediaType.APPLICATION_JSON_VALUE,
                                                 MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<?> addDetails(@RequestPart(value = "user") String user, @RequestParam(value="file") MultipartFile file) throws IOException, UserAlreadyExistsException {
        return new ResponseEntity<>(service.saveDetails(user,file), HttpStatus.CREATED);
    }

    @PutMapping(value="/details" , consumes = {MediaType.APPLICATION_JSON_VALUE,
                                                MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<?> updateDetails(@RequestPart(value = "user") String user, @RequestParam(value="file") MultipartFile file) throws IOException, UserNotFoundException {
        return new ResponseEntity<>(service.updateDetails(user,file), HttpStatus.CREATED);
    }

}
