package com.jhs.mokoji.controller.response;

import lombok.Builder;

public class GroupMember {
    private final String userId;
    private final String username;
    private final String userGroupRole;

    @Builder
    public GroupMember(String userId, String username, String userGroupRole) {
        this.userId = userId;
        this.username = username;
        this.userGroupRole = userGroupRole;
    }
}
