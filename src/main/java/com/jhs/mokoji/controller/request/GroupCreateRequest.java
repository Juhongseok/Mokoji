package com.jhs.mokoji.controller.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GroupCreateRequest {
    private String name;
    private String explanation;
    private String area;
    private int numberOfParticipation;
    private String interests;
}
