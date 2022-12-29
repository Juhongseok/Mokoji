package com.jhs.mokoji.service;

import com.jhs.mokoji.auth.CustomUser;
import com.jhs.mokoji.domain.UserGroup;
import com.jhs.mokoji.domain.compositeid.UserGroupId;
import com.jhs.mokoji.repository.UserGroupRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.jhs.mokoji.domain.UserGroup.newMember;

@Service
@Transactional
@RequiredArgsConstructor
public class UserGroupService {

    private final UserGroupRepository userGroupRepository;

    public void registerUser(CustomUser user, Long groupId) {
        saveUserGroup(user.getId(), groupId);
    }

    public void approveUser(String userId, Long groupId) {
        userGroupRepository.findById(createUserGroupId(userId, groupId))
                .ifPresentOrElse(UserGroup::toAssociate, () -> saveUserGroup(userId, groupId));
    }

    private void saveUserGroup(String userId, Long groupId) {
        userGroupRepository.save(newMember(createUserGroupId(userId, groupId)));
    }

    private UserGroupId createUserGroupId(String userId, Long groupId) {
        return new UserGroupId(userId, groupId);
    }
}
