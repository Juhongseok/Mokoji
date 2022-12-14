package com.jhs.mokoji.domain.baseentity;

import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@NoArgsConstructor
@MappedSuperclass
public class TimeAndPersonInfo extends TimeInfo{

    @Column(name = "CREATED_BY")
    private String createdBy;

    @Column(name = "MODIFIED_BY")
    private String modifiedBy;

    public TimeAndPersonInfo(String createdBy) {
        this.createdBy = createdBy;
        this.modifiedBy = createdBy;
    }
}