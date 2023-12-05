package com.example.blog.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageRequest {
    @GetMapping("/board")
    public String home() {
        return "html/index";
    }

    
}
