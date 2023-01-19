package com.jhs.mokoji.controller.request;

import com.jhs.mokoji.domain.Group;
import com.jhs.mokoji.domain.Notice;
import lombok.AllArgsConstructor;
import lombok.Data;
import java.time.LocalDate;

@Data
@AllArgsConstructor
public class NoticeCreateRequest {
    private String title;
    private String meetingTime;
    private String location;

    public Notice toEntityWithFk(Group group) {
        return new Notice(title, LocalDate.parse(meetingTime), location, group);
    }
}
