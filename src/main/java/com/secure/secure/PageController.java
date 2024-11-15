package com.secure.secure;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    @GetMapping("/Main")
    public String mainPage() {
        return "Main";  // Main.jsp 또는 main.html 파일을 찾음
    }

    @GetMapping("/Select")
    public String createAccountPage() {
        return "Select";
    }

}
