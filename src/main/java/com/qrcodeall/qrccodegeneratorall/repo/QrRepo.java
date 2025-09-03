package com.qrcodeall.qrccodegeneratorall.repo;




import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.qrcodeall.qrccodegeneratorall.dto.QrCodeDTO;
@Repository
public interface QrRepo extends MongoRepository<QrCodeDTO,String> {
    
}
