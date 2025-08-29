package com.qrcodeall.qrccodegeneratorall.service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.qrcodeall.qrccodegeneratorall.dto.QrCodeDTO;
import com.qrcodeall.qrccodegeneratorall.repo.QrRepo;

@Service
public class QrService {

    @Autowired
    private QrRepo qrRepo;
    
    public byte[] createQRCode(String type, String content, String fileUrl, long validMillis)
            throws WriterException, IOException {

        // 1. Generate token + expiry
        String token = UUID.randomUUID().toString();
        long expiryTime = System.currentTimeMillis() + validMillis;

        // 2. Save into DB
        QrCodeDTO qr = new QrCodeDTO();
        qr.setToken(token);
        qr.setType(type);
        qr.setContent(content);
        qr.setFileUrl(fileUrl);
        qr.setExpiryTime(expiryTime);
        qrRepo.save(qr);

        // 3. Encode only the token inside QR
        if (fileUrl != null && !fileUrl.startsWith("http://") && !fileUrl.startsWith("https://")) {
         fileUrl = "https://" + fileUrl;
            }

        String qrLink = "http://localhost:8080/qr/" + token;
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(qrLink, BarcodeFormat.QR_CODE, 300, 300);

        ByteArrayOutputStream pngOutputStream = new ByteArrayOutputStream();
        MatrixToImageWriter.writeToStream(bitMatrix, "PNG", pngOutputStream);

        // 4. Return QR image bytes
        return pngOutputStream.toByteArray();
    }

    

    public String scanQRCode(String token) {
       Optional<QrCodeDTO> qrtoken=qrRepo.findById(token);
       if (qrtoken.isPresent()) {
        QrCodeDTO qr = qrtoken.get();
        System.out.println("Fetched token: " + token + " → Content: " + qr.getContent());
        return qr.getContent();
    }else{
        System.out.println("Token not found: " + token);
        return "Token not found";
    }

    
    }
    
}
