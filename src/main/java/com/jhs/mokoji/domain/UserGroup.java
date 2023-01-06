package com.jhs.mokoji.domain;

import com.jhs.mokoji.domain.compositeid.UserGroupId;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Persistable;

import javax.persistence.*;

import static com.jhs.mokoji.domain.GroupRole.ROLE_UN_APPROVED;
import static javax.persistence.FetchType.LAZY;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserGroup implements Persistable<UserGroupId> {

    @EmbeddedId
    private UserGroupId id;

    @Enumerated(EnumType.STRING)
    private GroupRole groupRole;

    @MapsId("userId")
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "USER_ID")
    private User user;

    @MapsId("groupId")
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "GROUP_ID")
    private Group group;

    public static UserGroup newMember(UserGroupId id, User user, Group group) {
        return new UserGroup(id, ROLE_UN_APPROVED, user, group);
    }

    @Override
    public UserGroupId getId() {
        return this.id;
    }

    @Override
    public boolean isNew() {
        return groupRole.isNewMember();
    }

    public void toAssociate() {
        this.groupRole = GroupRole.ROLE_ASSOCIATE;
    }

    public void toRegular() {
        this.groupRole = GroupRole.ROLE_REGULAR;
    }
}
