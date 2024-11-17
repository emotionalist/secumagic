package com.secure.secure;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

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
    public ResponseEntity<Map<String, String>> processDataWithFile(
            @RequestParam("industry") String industry,
            @RequestParam("companySize") int companySize,
            @RequestParam("fileName") MultipartFile file) {

        if (file.isEmpty()) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", "File upload failed: No file provided.");
            return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
        }

        try {
            // 데이터 생성
            MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
            body.add("industry", industry);  // 업종 데이터 추가
            body.add("companySize", String.valueOf(companySize));  // 회사 크기 추가
            body.add("fileName", new ByteArrayResource(file.getBytes()) {
                @Override
                public String getFilename() {
                    return file.getOriginalFilename();
                }
            });

            // 헤더 설정
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.MULTIPART_FORM_DATA);

            // 요청 생성
            HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);

            // Python 서버 URL 확인
            String pythonServerUrl = "http://localhost:5001/processData";

            // Flask 서버로 요청 전송
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<Map> response = restTemplate.postForEntity(pythonServerUrl, requestEntity, Map.class);

            // 성공 응답 반환
            return new ResponseEntity<>(response.getBody(), HttpStatus.OK);
        } catch (Exception e) {
            // 에러 로깅 및 응답
            System.err.println("Error while communicating with Python server: " + e.getMessage());
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", "An error occurred while processing your request.");
            return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
