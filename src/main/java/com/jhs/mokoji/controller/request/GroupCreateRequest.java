package com.jhs.mokoji.controller.request;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
public class GroupCreateRequest {
    @NotBlank
    private String name;
    private String explanation;
    private String area;
    private Integer numberOfParticipation;
    private String interests;
}
