package com.jhs.mokoji.controller.response;

import com.jhs.mokoji.domain.User;
import lombok.Data;

@Data
public class UserInfo {
    private final String userId;
    private final String username;

    public UserInfo(User user) {
        this.userId = user.getId();
        this.username = user.getName();
    }
}
