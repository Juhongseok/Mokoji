package com.jhs.mokoji.domain;

import com.jhs.mokoji.controller.request.GroupCreateRequest;
import com.jhs.mokoji.domain.baseentity.TimeAndPersonInfo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Getter
@Table(name = "GROUPS")
@NoArgsConstructor
public class Group extends TimeAndPersonInfo {

    @Id @GeneratedValue(strategy = IDENTITY)
    private Long id;

    private String name;
    private String explanation;
    private String area;
    private int numberOfParticipation;
    private String interests;

    @Builder
    public Group(String name, String explanation, String area, int numberOfParticipation, String interests) {
        this.name = name;
        this.explanation = explanation;
        this.area = area;
        this.numberOfParticipation = numberOfParticipation;
        this.interests = interests;
    }

    public static Group of(GroupCreateRequest request) {
        return Group.builder()
                .name(request.getName())
                .explanation(request.getExplanation())
                .area(request.getArea())
                .numberOfParticipation(request.getNumberOfParticipation() == null ? 50 : request.getNumberOfParticipation())
                .interests(request.getInterests() == null ? "기타" : request.getInterests())
                .build();
    }
}
