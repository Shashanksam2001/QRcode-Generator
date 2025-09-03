package com.qrcodeall.qrccodegeneratorall.dto;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Document(collection = "userdto")
@Data

public class UserDTO {
    @Id
    private String id;
    private String username;
    private String email;
    private String password;
}
