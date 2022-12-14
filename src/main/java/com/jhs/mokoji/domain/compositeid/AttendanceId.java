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
public class AttendanceId implements Serializable {

    @Column(name = "USER_ID")
    private String userId;

    @Column(name = "NOTICE_ID")
    private Long noticeId;
}
