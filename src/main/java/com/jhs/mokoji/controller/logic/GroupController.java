package com.jhs.mokoji.controller.logic;

import com.jhs.mokoji.auth.CustomUser;
import com.jhs.mokoji.controller.request.GroupCreateRequest;
import com.jhs.mokoji.controller.response.GroupMembers;
import com.jhs.mokoji.controller.response.common.ResponseData;
import com.jhs.mokoji.controller.response.common.SingleResponseData;
import com.jhs.mokoji.service.GroupService;
import com.jhs.mokoji.service.UserGroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/group")
@RequiredArgsConstructor
public class GroupController {

    private final GroupService groupService;
    private final UserGroupService userGroupService;

    @PostMapping("/create")
    public ResponseData createGroup(GroupCreateRequest request, Authentication authentication) {
        CustomUser user = (CustomUser) authentication.getPrincipal();
        groupService.add(request, user);
        return SingleResponseData.of("ok");
    }

    @PostMapping("/{groupId}/sign")
    public ResponseData signGroup(Authentication authentication, @PathVariable Long groupId) {
        CustomUser user = (CustomUser) authentication.getPrincipal();
        userGroupService.registerUser(user, groupId);
        return SingleResponseData.of("ok");
    }

    @PostMapping("/{groupId}/approval/{userId}")
    public ResponseData approveMember(@PathVariable(name = "userId") String userId, @PathVariable(name = "groupId") Long groupId) {
        userGroupService.approveUser(userId, groupId);
        return SingleResponseData.of("ok");
    }

    @ResponseBody
    @GetMapping("/{groupId}/members")
    public ResponseData getMembers(@PathVariable Long groupId) {
        GroupMembers groupMembers = new GroupMembers(groupService.getMembers(groupId));
        return SingleResponseData.of(groupMembers);
    }
    
    @PostMapping("/{groupId}/exile/{userId}")
    public ResponseData expelMember (@PathVariable(name = "userId") String userId, @PathVariable(name = "groupId") Long groupId) {
        userGroupService.deleteUser(userId, groupId);
        return SingleResponseData.of("ok");
    }

    @PostMapping("/{groupId}/advancement/{userId}")
    public ResponseData advanceMember (@PathVariable(name = "userId") String userId, @PathVariable(name = "groupId") Long groupId) {
        userGroupService.advanceUser(userId, groupId);
        return SingleResponseData.of("ok");
    }
}
