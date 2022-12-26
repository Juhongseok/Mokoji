package com.jhs.mokoji.controller;

import com.jhs.mokoji.controller.request.UserSignUpRequest;
import com.jhs.mokoji.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/sign")
    public String signUpPage(){
        return "signUp";
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @PostMapping("/sign")
    public String signUp(UserSignUpRequest request){
        userService.save(request);
        return "redirect:/";
    }
}
