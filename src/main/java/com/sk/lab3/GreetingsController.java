package com.sk.lab3;


import java.time.LocalTime;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GreetingsController {

	@GetMapping("/")
    public String greeting(Model model) {
        LocalTime now = LocalTime.now();
        String name = "Sanish Kadariya"; 
        String message = (now.isBefore(LocalTime.of(12, 0))) ?
                "Good morning, " + name + ", Welcome to COMP367" :
                "Good afternoon, " + name + ", Welcome to COMP367";
        model.addAttribute("message", message);
        return "greetings";
    }
}