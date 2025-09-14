package com.qrcodeall.qrccodegeneratorall.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.qrcodeall.qrccodegeneratorall.dto.QrCodeDTO;
@Repository
public interface QrRepo extends JpaRepository<QrCodeDTO,String> {
    
}
