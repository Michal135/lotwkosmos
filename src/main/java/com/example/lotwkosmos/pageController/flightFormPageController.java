package com.example.lotwkosmos.pageController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class flightFormPageController {


    @GetMapping("/flightForm")
    public String flightForm(){
        return "flightForm";
    }
}
