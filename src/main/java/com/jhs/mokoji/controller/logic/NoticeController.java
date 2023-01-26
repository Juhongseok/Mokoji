package com.jhs.mokoji.controller.logic;

import com.jhs.mokoji.controller.request.NoticeCreateRequest;
import com.jhs.mokoji.controller.request.NoticeUpdateRequest;
import com.jhs.mokoji.controller.response.common.ResponseData;
import com.jhs.mokoji.controller.response.common.SingleResponseData;
import com.jhs.mokoji.service.NoticeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/notice")
@RequiredArgsConstructor
public class NoticeController {

    private final NoticeService noticeService;

    @PostMapping("/create/group/{groupId}")
    public ResponseData createNotice(NoticeCreateRequest request, @PathVariable Long groupId) {
        noticeService.create(request, groupId);
        return SingleResponseData.of("ok");
    }

    @PostMapping("/{noticeId}/update")
    public ResponseData updateNotice(NoticeUpdateRequest request, @PathVariable Long noticeId){
        noticeService.update(noticeId, request);
        return SingleResponseData.of("ok");
    }

    @PostMapping("/{noticeId}/delete")
    public ResponseData deleteNotice(@PathVariable Long noticeId){
        noticeService.delete(noticeId);
        return SingleResponseData.of("ok");
    }

    @PostMapping("/{noticeId}/attend/user/{userId}")
    public ResponseData attendMeeting(@PathVariable Long noticeId, @PathVariable String userId, @RequestParam String attention){
        noticeService.voteMeeting(noticeId, userId, attention);
        return SingleResponseData.of("ok");
    }
}
