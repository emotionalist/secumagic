package com.secure.secure;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

@Controller
public class PageController {

    @GetMapping("/main")
    public String mainPage() {
        return "Main";  // Main.html 템플릿 반환
    }

    @GetMapping("/select")
    public String selectPage() {
        return "Select";  // Select.html 템플릿 반환
    }

    @PostMapping("/processData")
    @ResponseBody
    public ResponseEntity<Map<String, String>> processData(
            @RequestParam("industry") String industry,
            @RequestParam("companySize") int companySize,  // 수정된 변수 이름
            @RequestParam("file") MultipartFile file) {

        // 파일이 비어 있는 경우 에러 응답 반환
        if (file.isEmpty()) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", "File upload failed: No file provided.");
            return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
        }

        // 파일 처리 로직 (예: 파일 이름 반환)
        String fileName = file.getOriginalFilename();

        // 응답 데이터 생성
        Map<String, String> responseData = new HashMap<>();
        responseData.put("industry", industry);
        responseData.put("companySize", String.valueOf(companySize));
        responseData.put("fileName", fileName);

        // JSON 형식의 응답 반환
        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }
}
