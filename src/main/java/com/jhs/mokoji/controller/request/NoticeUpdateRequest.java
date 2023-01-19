package com.jhs.mokoji.controller.request;

import lombok.Data;

@Data
public class NoticeUpdateRequest {
    private String title;
    private String meetingTime;
    private String location;
}
