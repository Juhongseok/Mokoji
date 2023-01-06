package com.jhs.mokoji.controller.response;

import java.util.List;

public class GroupMembers {
    private final int totalCount;
    private final List<GroupMember> members;

    public GroupMembers(List<GroupMember> members) {
        this.members = members;
        this.totalCount = members.size();
    }
}
