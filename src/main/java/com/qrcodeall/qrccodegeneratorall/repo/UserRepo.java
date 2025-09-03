package com.qrcodeall.qrccodegeneratorall.repo;

import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;
import com.qrcodeall.qrccodegeneratorall.dto.UserDTO;


public interface UserRepo extends MongoRepository<UserDTO,String> {
    Optional<UserDTO> findByusername(String username);
    Optional<UserDTO> findByemail(String email);
}
