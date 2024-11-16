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
            @RequestParam("member2") String member2,
            @RequestParam("member3") String member3,
            @RequestParam("file") MultipartFile file) {

        // 파일이 비어 있는 경우 에러 응답 반환
        if (file.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        // 응답 데이터 생성
        Map<String, String> responseData = new HashMap<>();
        responseData.put("industry", industry);
        responseData.put("member2", member2);
        responseData.put("member3", member3);
        responseData.put("fileName", file.getOriginalFilename());

        // JSON 형식의 응답 반환
        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }
}
