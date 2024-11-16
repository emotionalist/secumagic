package com.secure.secure;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@Controller
public class PageController {

    @GetMapping("/main")
    public String mainPage() {
        return "Main";  // Main.jsp 또는 main.html 파일을 찾음
    }

    @GetMapping("/select")
    public String selectPage() {
        return "Select";
    }

    @PostMapping("/processData")
    @ResponseBody
    public ResponseEntity<String> processData(
            @RequestParam("industry") String industry,
            @RequestParam("member2") String member2,
            @RequestParam("member3") String member3,
            @RequestParam("file") MultipartFile file) {

        if (file.isEmpty()) {
            return new ResponseEntity<>("No file selected", HttpStatus.BAD_REQUEST);
        }

        // 간단한 응답 메시지 생성
        String responseMessage = String.format("Industry: %s, Team Member 2: %s, Team Member 3: %s. File uploaded: %s",
                industry, member2, member3, file.getOriginalFilename());

        return new ResponseEntity<>(responseMessage, HttpStatus.OK);
    }

}
