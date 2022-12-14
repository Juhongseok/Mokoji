package com.jhs.mokoji.domain.compositeid;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class UserGroupId implements Serializable {

    @Column(name = "USER_ID")
    private String userId;

    @Column(name = "GROUP_ID")
    private Long groupId;
}
