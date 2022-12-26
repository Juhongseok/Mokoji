package com.jhs.mokoji.service;

import com.jhs.mokoji.auth.CustomUser;
import com.jhs.mokoji.controller.request.UserSignUpRequest;
import com.jhs.mokoji.domain.User;
import com.jhs.mokoji.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return new CustomUser(
                userRepository.findUserById(username)
                    .orElseThrow(() -> new UsernameNotFoundException("Wrong userId"))
        );
    }

    public void save(UserSignUpRequest request) {
        request.encryptionPassword(passwordEncoder);
        userRepository.save(User.of(request));
    }
}
