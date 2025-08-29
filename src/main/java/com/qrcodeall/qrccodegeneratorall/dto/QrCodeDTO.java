package com.qrcodeall.qrccodegeneratorall.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class QrCodeDTO {
    @Id
    private String token;       // Unique token (UUID)
    private String type;        // TEXT, LINK, IMAGE, PDF
    private String content;     // Text or URL
    private long expiryTime;    // Epoch millis
    private String fileUrl;     // For IMAGE/PDF (user-provided sharing link)  
    
}
