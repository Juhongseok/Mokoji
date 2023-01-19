package com.jhs.mokoji.controller.logic;

import com.jhs.mokoji.auth.CustomUser;
import com.jhs.mokoji.controller.request.GroupCreateRequest;
import com.jhs.mokoji.controller.response.GroupMembers;
import com.jhs.mokoji.service.GroupService;
import com.jhs.mokoji.service.UserGroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/group")
@RequiredArgsConstructor
public class GroupController {

    private final GroupService groupService;
    private final UserGroupService userGroupService;

    @PostMapping("/create")
    public String createGroup(GroupCreateRequest request, Authentication authentication) {
        CustomUser user = (CustomUser) authentication.getPrincipal();
        groupService.add(request, user);
        return "redirect:/";
    }

    @PostMapping("/{groupId}/sign")
    public String signGroup(Authentication authentication, @PathVariable Long groupId) {
        CustomUser user = (CustomUser) authentication.getPrincipal();
        userGroupService.registerUser(user, groupId);
        return "redirect:/";
    }

    @PostMapping("/{groupId}/approval/{userId}")
    public String approveMember(@PathVariable(name = "userId") String userId, @PathVariable(name = "groupId") Long groupId) {
        userGroupService.approveUser(userId, groupId);
        return "redirect:/";
    }

    @ResponseBody
    @GetMapping("/{groupId}/members")
    public GroupMembers getMembers(@PathVariable Long groupId, Model model) {
        GroupMembers groupMembers = new GroupMembers(groupService.getMembers(groupId));
        model.addAttribute("members", groupMembers);
        return groupMembers;
    }
    
    @PostMapping("/{groupId}/exile/{userId}")
    public String expelMember (@PathVariable(name = "userId") String userId, @PathVariable(name = "groupId") Long groupId) {
        userGroupService.deleteUser(userId, groupId);
        return "redirect:/";
    }

    @PostMapping("/{groupId}/advancement/{userId}")
    public String advanceMember (@PathVariable(name = "userId") String userId, @PathVariable(name = "groupId") Long groupId) {
        userGroupService.advanceUser(userId, groupId);
        return "redirect:/";
    }
}
