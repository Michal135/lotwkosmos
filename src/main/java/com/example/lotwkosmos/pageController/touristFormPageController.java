package com.example.lotwkosmos.pageController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class touristFormPageController {

    @GetMapping("/touristForm")
    public String touristForm(){
        return "touristForm";
    }
}
