package com.example.lotwkosmos.pageController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class addingFlightToTouristPageController {
    @GetMapping("/addingFlightToTourist")
    public String flights(){
        return "addingFlightToTourist";
    }
}
