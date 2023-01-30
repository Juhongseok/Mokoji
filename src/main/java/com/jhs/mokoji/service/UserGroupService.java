package com.jhs.mokoji.service;

import com.jhs.mokoji.auth.CustomUser;
import com.jhs.mokoji.controller.response.error.ErrorCode;
import com.jhs.mokoji.domain.Group;
import com.jhs.mokoji.domain.User;
import com.jhs.mokoji.domain.UserGroup;
import com.jhs.mokoji.domain.compositeid.UserGroupId;
import com.jhs.mokoji.repository.GroupRepository;
import com.jhs.mokoji.repository.UserGroupRepository;
import com.jhs.mokoji.repository.UserRepository;
import com.jhs.mokoji.service.exception.DuplicatedEntityException;
import com.jhs.mokoji.service.exception.EntityNotFoundException;
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

    private void saveUserGroupWithId(String userId, Long groupId) {
        User user = userRepository.getReferenceById(userId);
        Group group = groupRepository.getReferenceById(groupId);
        UserGroupId userGroupId = createUserGroupId(userId, groupId);
        validateDuplicateGroupMember(userGroupId);
        userGroupRepository.save(newMember(userGroupId, user, group));
    }

    private void validateDuplicateGroupMember(UserGroupId userGroupId) {
        if (userGroupRepository.findById(userGroupId).isPresent()) {
            throw new DuplicatedEntityException(ErrorCode.DUPLICATE_USER);
        }
    }

    private void changeMemberRole(String userId, Long groupId, Consumer<? super UserGroup> action) {
        userGroupRepository.findById(createUserGroupId(userId, groupId))
                .ifPresentOrElse(
                        action,
                        () -> new EntityNotFoundException(ErrorCode.USER_NOT_FOUND)
                );
    }

    private UserGroupId createUserGroupId(String userId, Long groupId) {
        return new UserGroupId(userId, groupId);
    }
}
