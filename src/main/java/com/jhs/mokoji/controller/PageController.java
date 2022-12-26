package com.jhs.mokoji.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    @GetMapping("/sign")
    public String signUpPage(){
        return "signUp";
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }
}
