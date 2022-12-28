package com.jhs.mokoji.service;

import com.jhs.mokoji.controller.request.GroupCreateRequest;
import com.jhs.mokoji.domain.Group;
import com.jhs.mokoji.repository.GroupRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class GroupService {

    private final GroupRepository groupRepository;

    @Transactional
    public void add(GroupCreateRequest request) {
        groupRepository.save(Group.of(request));
    }
}
