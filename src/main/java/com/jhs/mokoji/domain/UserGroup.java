package com.jhs.mokoji.domain;

import com.jhs.mokoji.domain.compositeid.UserGroupId;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Persistable;

import javax.persistence.*;

import static com.jhs.mokoji.domain.GroupRole.ROLE_UN_APPROVED;

@Entity
@NoArgsConstructor
@AllArgsConstructor
public class UserGroup implements Persistable<UserGroupId> {

    @EmbeddedId
    private UserGroupId id;

    @Enumerated(EnumType.STRING)
    private GroupRole groupRole;

    public static UserGroup newMember(UserGroupId id) {
        return new UserGroup(id, ROLE_UN_APPROVED);
    }

    @Override
    public UserGroupId getId() {
        return this.id;
    }

    @Override
    public boolean isNew() {
        return groupRole.isNewMember();
    }
}
