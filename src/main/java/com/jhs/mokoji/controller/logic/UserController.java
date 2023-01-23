package com.jhs.mokoji.controller.logic;

import com.jhs.mokoji.controller.request.UserSignUpRequest;
import com.jhs.mokoji.controller.response.UserInfo;
import com.jhs.mokoji.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/sign")
    public String signUp(UserSignUpRequest request){
        userService.save(request);
        return "redirect:/";
    }

    @GetMapping("/{userId}")
    public String getUserInfo(@PathVariable String userId, Model model){
        UserInfo userInfo = userService.getUserInfo(userId);
        model.addAttribute("userInfo", userInfo);
        return "user/userInfo";
    }
}
