package com.jhs.mokoji.service;

import com.jhs.mokoji.controller.request.GroupCreateRequest;
import com.jhs.mokoji.controller.response.GroupMember;
import com.jhs.mokoji.domain.Group;
import com.jhs.mokoji.domain.User;
import com.jhs.mokoji.repository.GroupRepository;
import com.jhs.mokoji.repository.UserGroupRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class GroupService {

    private final GroupRepository groupRepository;
    private final UserGroupRepository userGroupRepository;

    @Transactional
    public void add(GroupCreateRequest request) {
        groupRepository.save(Group.of(request));
    }

    public List<GroupMember> getMembers(Long groupId) {
        return userGroupRepository.findAllByIdGroupId(groupId)
                .stream()
                .map(userGroup -> {
                    User user = userGroup.getUser();
                    return new GroupMember(user.getId(), user.getName(), userGroup.getGroupRole().name());
                })
                .collect(Collectors.toList());
    }
}
