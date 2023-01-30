package com.jhs.mokoji.service;

import com.jhs.mokoji.auth.CustomUser;
import com.jhs.mokoji.controller.request.UserSignUpRequest;
import com.jhs.mokoji.controller.response.UserInfo;
import com.jhs.mokoji.controller.response.error.ErrorCode;
import com.jhs.mokoji.domain.User;
import com.jhs.mokoji.repository.UserRepository;
import com.jhs.mokoji.service.exception.DuplicatedEntityException;
import com.jhs.mokoji.service.exception.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return new CustomUser(
                userRepository.findById(username)
                    .orElseThrow(() -> new UsernameNotFoundException("Wrong userId"))
        );
    }

    public void save(UserSignUpRequest request) {
        validationDuplicateId(request.getUserId());
        User user = request.toEntity();
        user.encryptionPassword(passwordEncoder);
        userRepository.save(user);
    }

    public UserInfo getUserInfo(String userId) {
        return new UserInfo(findUser(userId));
    }

    private User findUser(String userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException(ErrorCode.USER_NOT_FOUND));
    }

    private void validationDuplicateId(String userId) {
        Optional<User> findUser = userRepository.findById(userId);
        if (findUser.isPresent()) {
            throw new DuplicatedEntityException(ErrorCode.DUPLICATE_USER);
        }
    }
}
