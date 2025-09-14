package com.qrcodeall.qrccodegeneratorall.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.qrcodeall.qrccodegeneratorall.dto.UserDTO;


public interface UserRepo extends JpaRepository<UserDTO,String> {
    Optional<UserDTO> findByusername(String username);
    Optional<UserDTO> findByemail(String email);
}
