package com.secure.secure;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

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
    public String processData(@RequestBody Map<String, String> requestData) {
        String industry = requestData.get("industry");
        String member2 = requestData.get("member2");
        String member3 = requestData.get("member3");

        // 간단한 로직 예시
        String responseMessage = "Selected Industry: " + industry + ", Team Member 2: " + member2 + ", Team Member 3: " + member3;

        // OpenAI API와 연동할 경우, 여기서 API 호출을 수행할 수 있습니다.
        // String responseMessage = openAIClient.getChatCompletion(responseMessage);

        return responseMessage;
    }

}
