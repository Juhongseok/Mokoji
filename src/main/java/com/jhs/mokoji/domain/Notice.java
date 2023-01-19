package com.jhs.mokoji.domain;

import com.jhs.mokoji.domain.baseentity.TimeAndPersonInfo;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.util.StringUtils;

import javax.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.CascadeType.PERSIST;
import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Getter
@DynamicUpdate
@NoArgsConstructor
public class Notice extends TimeAndPersonInfo {
    @Id @GeneratedValue(strategy = IDENTITY)
    private Long id;
    private String title;
    private LocalDate meetingTime;
    private String location;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "GROUP_ID")
    private Group group;

    @OneToMany(mappedBy = "notice", fetch = LAZY, cascade = PERSIST)
    private List<Attendance> attendances = new ArrayList<>();

    public Notice(String title, LocalDate meetingTime, String location, Group group) {
        this.title = title;
        this.meetingTime = meetingTime;
        this.location = location;
        this.group = group;
    }

    public void update(String title, String meetingTime, String location) {
        this.title = title;
        this.meetingTime = StringUtils.hasText(meetingTime) ? LocalDate.parse(meetingTime) : this.meetingTime;
        this.location = location;
    }

    public void addAttendance(Attendance attendance) {
        attendances.add(attendance);
    }
}
