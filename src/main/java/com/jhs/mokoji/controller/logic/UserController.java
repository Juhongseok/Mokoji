package com.jhs.mokoji.controller.logic;

import com.jhs.mokoji.auth.CustomUser;
import com.jhs.mokoji.controller.request.UserSignUpRequest;
import com.jhs.mokoji.controller.response.common.ResponseData;
import com.jhs.mokoji.controller.response.common.SingleResponseData;
import com.jhs.mokoji.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/sign")
    public ResponseData signUp(@RequestBody UserSignUpRequest request){
        userService.save(request);
        return SingleResponseData.of("ok");
    }

    @GetMapping
    public ResponseData getUserInfo(Authentication authentication){
        CustomUser user = (CustomUser) authentication.getPrincipal();
        return SingleResponseData.of(userService.getUserInfo(user.getId()));
    }
}
