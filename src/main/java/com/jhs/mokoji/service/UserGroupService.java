package com.jhs.mokoji.service;

import com.jhs.mokoji.auth.CustomUser;
import com.jhs.mokoji.domain.compositeid.UserGroupId;
import com.jhs.mokoji.repository.UserGroupRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.jhs.mokoji.domain.UserGroup.newMember;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserGroupService {

    private final UserGroupRepository userGroupRepository;

    @Transactional
    public void registerUser(CustomUser user, Long groupId) {
        UserGroupId id = new UserGroupId(user.getUser().getId(), groupId);
        userGroupRepository.save(newMember(id));
    }
}
