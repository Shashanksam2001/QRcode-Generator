package com.qrcodeall.qrccodegeneratorall.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "UsersInfo")
public class UserDTO {
    @Id
    private String id;
    private String username;
    private String email;
    private String password;
}
