package com.learn.userservice.model;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;


@NoArgsConstructor
@Setter
@Getter
@AllArgsConstructor
@Entity
@Table(name = "USERS_DETAILS")
public class User {

    @Id
    private String emailId;
    private String username;
    private String password;

    @Transient
    private String fileName;
    @Transient
    private String contentType;

    @Lob
    private byte[] data;

    public User(String fileName, String contentType, byte[] data) {
        this.fileName=fileName;
        this.contentType=contentType;
        this.data=data;
    }
}
