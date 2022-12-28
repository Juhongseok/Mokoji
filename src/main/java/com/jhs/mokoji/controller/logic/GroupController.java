package com.jhs.mokoji.controller.logic;

import com.jhs.mokoji.auth.CustomUser;
import com.jhs.mokoji.controller.request.GroupCreateRequest;
import com.jhs.mokoji.service.GroupService;
import com.jhs.mokoji.service.UserGroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/group")
@RequiredArgsConstructor
public class GroupController {

    private final GroupService groupService;
    private final UserGroupService userGroupService;

    @PostMapping("/create")
    public String createGroup(GroupCreateRequest request){
        groupService.add(request);
        return "redirect:/";
    }

    @PostMapping("/sign/{groupId}")
    public String signGroup(Authentication authentication, @PathVariable Long groupId){
        CustomUser user = (CustomUser) authentication.getPrincipal();
        userGroupService.registerUser(user, groupId);
        return "redirect:/";
    }
}
