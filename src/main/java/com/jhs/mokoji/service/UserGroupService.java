package com.jhs.mokoji.service;

import com.jhs.mokoji.auth.CustomUser;
import com.jhs.mokoji.domain.Group;
import com.jhs.mokoji.domain.User;
import com.jhs.mokoji.domain.UserGroup;
import com.jhs.mokoji.domain.compositeid.UserGroupId;
import com.jhs.mokoji.repository.GroupRepository;
import com.jhs.mokoji.repository.UserGroupRepository;
import com.jhs.mokoji.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.function.Consumer;

import static com.jhs.mokoji.domain.UserGroup.newMember;

@Service
@Transactional
@RequiredArgsConstructor
public class UserGroupService {

    private final UserGroupRepository userGroupRepository;
    private final UserRepository userRepository;
    private final GroupRepository groupRepository;

    public void registerUser(CustomUser user, Long groupId) {
        saveUserGroupWithId(user.getId(), groupId);
    }

    public void approveUser(String userId, Long groupId) {
        changeMemberRole(userId, groupId, UserGroup::toAssociate);
    }

    public void advanceUser(String userId, Long groupId) {
        changeMemberRole(userId, groupId, UserGroup::toRegular);
    }

    public void deleteUser(String userId, Long groupId) {
        userGroupRepository.deleteById(createUserGroupId(userId, groupId));
    }

    private UserGroupId createUserGroupId(String userId, Long groupId) {
        return new UserGroupId(userId, groupId);
    }

    private void saveUserGroupWithId(String userId, Long groupId) {
        User user = userRepository.getReferenceById(userId);
        Group group = groupRepository.getReferenceById(groupId);
        userGroupRepository.save(newMember(createUserGroupId(userId, groupId), user, group));
    }

    private void changeMemberRole(String userId, Long groupId, Consumer<? super UserGroup> action) {
        userGroupRepository.findById(createUserGroupId(userId, groupId))
                .ifPresentOrElse(
                        action,
                        IllegalArgumentException::new
                );
    }
}
