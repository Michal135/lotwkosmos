package com.example.lotwkosmos.pageController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class indexPageController {

    @GetMapping("/mainPage")
        public String mainPageController(){
        return "mainPage";
    }

}
