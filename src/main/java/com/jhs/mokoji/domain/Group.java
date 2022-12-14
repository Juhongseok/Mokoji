package com.jhs.mokoji.domain;

import com.jhs.mokoji.domain.baseentity.TimeAndPersonInfo;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Group extends TimeAndPersonInfo {

    @Id @GeneratedValue(strategy = IDENTITY)
    private Long id;

    private String name;
    private String explanation;
    private String area;
    private int numberOfParticipation;
    private String interests;
}
