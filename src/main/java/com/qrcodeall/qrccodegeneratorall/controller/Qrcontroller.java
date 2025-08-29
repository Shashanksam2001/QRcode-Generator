package com.qrcodeall.qrccodegeneratorall.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.qrcodeall.qrccodegeneratorall.service.QrService;

@RestController
@RequestMapping("/qr")
public class Qrcontroller {
    @Autowired
    private QrService qrCodeService;

    
   @RequestMapping(value = "/generate", method = {RequestMethod.GET, RequestMethod.POST})
public ResponseEntity<byte[]> generateQRCode(
        @RequestParam String type,
        @RequestParam(required = false) String content,
        @RequestParam(required = false) String fileUrl,
        @RequestParam(defaultValue = "86400000") long validMillis
) {
    try {
        byte[] qrImage = qrCodeService.createQRCode(type, content, fileUrl, validMillis);

        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_PNG)
                .body(qrImage);

    } catch (Exception e) {
        return ResponseEntity.badRequest().body(null);
    }
}

    @GetMapping("/{token}")
public ResponseEntity<?> showMessage(@PathVariable String token) {
    String msg = qrCodeService.scanQRCode(token);
    if (msg.startsWith("http://") || msg.startsWith("https://")) {
        return ResponseEntity.status(HttpStatus.FOUND)  // 302 redirect
                .location(URI.create(msg))
                .build();
    }

    // Otherwise just return the message as text
    return ResponseEntity.ok()
            .contentType(MediaType.TEXT_PLAIN)
            .body(msg);

    
}
}


