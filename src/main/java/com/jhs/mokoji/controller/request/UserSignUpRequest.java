package com.jhs.mokoji.controller.request;

import com.jhs.mokoji.domain.Role;
import com.jhs.mokoji.domain.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter @Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserSignUpRequest {
    private String userId;
    private String password;
    private String name;

    public User toEntity() {
        return new User(userId, password, name, Role.ROLE_USER);
    }
}
