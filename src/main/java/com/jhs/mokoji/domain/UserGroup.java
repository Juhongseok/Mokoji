package com.jhs.mokoji.domain;

import com.jhs.mokoji.domain.compositeid.UserGroupId;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

@Entity
@NoArgsConstructor
@AllArgsConstructor
public class UserGroup {
    @EmbeddedId
    private UserGroupId id;

    @MapsId("userId")
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "USER_ID")
    private User user;

    @MapsId("groupId")
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "GROUP_ID")
    private Group group;

    private String groupRole;
}
