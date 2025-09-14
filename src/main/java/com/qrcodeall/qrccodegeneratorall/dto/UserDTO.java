package com.qrcodeall.qrccodegeneratorall.dto;


import jakarta.persistence.Id;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data

public class UserDTO {
    @Id
    private String id;
    private String username;
    private String email;
    private String password;
}
