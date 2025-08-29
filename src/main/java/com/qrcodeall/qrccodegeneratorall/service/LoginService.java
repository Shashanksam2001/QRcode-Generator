package com.qrcodeall.qrccodegeneratorall.service;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.qrcodeall.qrccodegeneratorall.dto.UserDTO;
import com.qrcodeall.qrccodegeneratorall.pojo.login.Login;
import com.qrcodeall.qrccodegeneratorall.pojo.login.Register;
import com.qrcodeall.qrccodegeneratorall.repo.UserRepo;

@Service
public class LoginService {
    @Autowired
    private UserRepo userRepo;

    @Bean
    private PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    public String registerUser(Register register){
        if(userRepo.findByUsername(register.getUsername()).isPresent()){
            return "UserName"+register.getUsername()+ "already present";
        }
        if(userRepo.findByemail(register.getEmail()).isPresent()){
            return "Email is already registered";
        }
        String userId=UUID.randomUUID().toString();
        UserDTO user=new UserDTO();
        user.setId(userId);
        user.setUsername(register.getUsername());
        user.setEmail(register.getEmail());
        String pwd=passwordEncoder().encode(register.getPassword());
        user.setPassword(pwd);
        userRepo.save(user);
        return "Registered Successfully"; 

    }

    public String loginUser(Login login){
        Optional<UserDTO> user=userRepo.findByUsername(login.getUsername());
        
        if(user.isPresent() && passwordEncoder().matches(login.getPassword(), user.get().getPassword()) ){
            return "Login successful!";
        }
        return "Invalid username or password!";
    }
    
}
