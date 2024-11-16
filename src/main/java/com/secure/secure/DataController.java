package com.secure.secure;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.IOException;

@RestController
public class DataController {

    @PostMapping("/uploadData")
    public ResponseEntity<String> handleFormSubmission(
            @RequestParam("industry") String industry,
            @RequestParam("member2") String member2,
            @RequestParam("member3") String member3,
            @RequestParam("file") MultipartFile file) {

        // 파일이 비어 있는지 확인
        if (file.isEmpty()) {
            return new ResponseEntity<>("File not selected", HttpStatus.BAD_REQUEST);
        }

        try {
            // 파일 저장 디렉토리 설정
            String uploadDir = "uploads/";
            File uploadDirectory = new File(uploadDir);
            if (!uploadDirectory.exists()) {
                uploadDirectory.mkdirs(); // 디렉토리가 없으면 생성
            }

            // 업로드된 파일 저장
            String filePath = uploadDir + file.getOriginalFilename();
            File destinationFile = new File(filePath);
            file.transferTo(destinationFile);

            // 처리된 결과 메시지 생성
            String responseMessage = String.format(
                    "Industry: %s, Team Member 2: %s, Team Member 3: %s. File '%s' uploaded successfully.",
                    industry, member2, member3, file.getOriginalFilename()
            );

            return new ResponseEntity<>(responseMessage, HttpStatus.OK);
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>("File upload failed", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
