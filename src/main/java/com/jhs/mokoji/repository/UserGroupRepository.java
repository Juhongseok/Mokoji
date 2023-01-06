package com.jhs.mokoji.repository;

import com.jhs.mokoji.domain.UserGroup;
import com.jhs.mokoji.domain.compositeid.UserGroupId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserGroupRepository extends JpaRepository<UserGroup, UserGroupId> {
    @Query("select ug from UserGroup ug join fetch ug.group g where ug.id.groupId = :groupId")
    List<UserGroup> findAllByIdGroupId(@Param("groupId") Long groupId);
}
