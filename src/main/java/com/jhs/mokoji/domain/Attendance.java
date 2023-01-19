package com.jhs.mokoji.domain;

import com.jhs.mokoji.domain.compositeid.AttendanceId;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.EnumType.STRING;
import static javax.persistence.FetchType.LAZY;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "USER_NOTICE_ATTENDANCE")
public class Attendance {
    @EmbeddedId
    private AttendanceId id;

    @MapsId("userId")
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "USER_ID")
    private User user;

    @MapsId("noticeId")
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "NOTICE_ID")
    private Notice notice;

    @Enumerated(STRING)
    private VoteAttendance attendance;

    public Attendance(User user, Notice notice, String attention) {
        this.id = new AttendanceId(user.getId(), notice.getId());
        this.user = user;
        this.notice = notice;
        this.attendance = VoteAttendance.of(attention);
    }
}
