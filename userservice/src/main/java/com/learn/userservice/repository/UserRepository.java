package com.learn.userservice.repository;

import com.learn.userservice.model.User;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

@Repository
public interface UserRepository extends CrudRepository<User,String> {

}
