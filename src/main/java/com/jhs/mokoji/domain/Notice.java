package com.jhs.mokoji.domain;

import com.jhs.mokoji.domain.baseentity.TimeAndPersonInfo;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.time.LocalDate;

import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;

@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Notice extends TimeAndPersonInfo {
    @Id @GeneratedValue(strategy = IDENTITY)
    private Long id;
    private String title;
    private LocalDate meetingTime;
    private String location;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "GROUP_ID")
    private Group group;
}
