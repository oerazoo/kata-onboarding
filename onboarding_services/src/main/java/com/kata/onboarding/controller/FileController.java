package com.kata.onboarding.controller;

import com.kata.onboarding.service.FileService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/files") @AllArgsConstructor
public class FileController {

    private final FileService fileService;

    @GetMapping("/create")
    public ResponseEntity<byte[]> createFile(){

        Map<String, Object> dataMap = new HashMap<>();

        dataMap.put("pqrId", "1-6000001");
        dataMap.put("description", "PQR de prueba");


        byte[] pdfBytes = fileService.generate("PQR", dataMap);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=pqr.pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .body(pdfBytes);
    }


}
