package com.jhs.mokoji.service;

import com.jhs.mokoji.auth.CustomUser;
import com.jhs.mokoji.controller.request.GroupCreateRequest;
import com.jhs.mokoji.controller.response.GroupMember;
import com.jhs.mokoji.controller.response.error.ErrorCode;
import com.jhs.mokoji.domain.Group;
import com.jhs.mokoji.domain.User;
import com.jhs.mokoji.domain.UserGroup;
import com.jhs.mokoji.repository.GroupRepository;
import com.jhs.mokoji.repository.UserGroupRepository;
import com.jhs.mokoji.service.exception.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class GroupService {

    private final GroupRepository groupRepository;
    private final UserGroupRepository userGroupRepository;

    public void add(GroupCreateRequest request, CustomUser user) {
        Group group = Group.of(request);
        groupRepository.save(group);
        userGroupRepository.save(UserGroup.manager(user, group));
    }

    @Transactional(readOnly = true)
    public List<GroupMember> getMembers(Long groupId) {
        return userGroupRepository.findAllByIdGroupId(groupId)
                .orElseThrow(() -> new EntityNotFoundException(ErrorCode.GROUP_NOT_FOUND))
                .stream()
                .map(userGroup -> {
                    User user = userGroup.getUser();
                    return new GroupMember(user.getId(), user.getName(), userGroup.getGroupRole().name());
                })
                .collect(Collectors.toList());
    }
}
