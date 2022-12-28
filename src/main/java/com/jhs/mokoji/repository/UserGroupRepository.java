package com.jhs.mokoji.repository;

import com.jhs.mokoji.domain.UserGroup;
import com.jhs.mokoji.domain.compositeid.UserGroupId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserGroupRepository extends JpaRepository<UserGroup, UserGroupId> {
}
