package com.jhs.mokoji.controller.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.crypto.password.PasswordEncoder;

@Setter @Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserSignUpRequest {
    private String userId;
    private String password;
    private String name;

    public void encryptionPassword(PasswordEncoder passwordEncoder) {
        this.password = passwordEncoder.encode(this.password);
    }
}
