package com.binarytree.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String indexPage() {
        return "index"; // This should load src/main/resources/templates/index.html
    }
}
