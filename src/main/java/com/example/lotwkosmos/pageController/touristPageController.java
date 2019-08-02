package com.example.lotwkosmos.pageController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class touristPageController {

    @GetMapping("/touristsPage")
    public String touristPage(){
        return "touristsPage";
    }
}
