package com.jhs.mokoji.controller.logic;

import com.jhs.mokoji.controller.request.NoticeCreateRequest;
import com.jhs.mokoji.controller.request.NoticeUpdateRequest;
import com.jhs.mokoji.service.NoticeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/notice")
@RequiredArgsConstructor
public class NoticeController {

    private final NoticeService noticeService;

    @PostMapping("/create/group/{groupId}")
    public String createNotice(NoticeCreateRequest request, @PathVariable Long groupId) {
        noticeService.create(request, groupId);
        return "redirect:/";
    }

    @PostMapping("/{noticeId}/update")
    public String updateNotice(NoticeUpdateRequest request, @PathVariable Long noticeId){
        noticeService.update(noticeId, request);
        return "redirect:/";
    }

    @PostMapping("/{noticeId}/delete")
    public String deleteNotice(@PathVariable Long noticeId){
        noticeService.delete(noticeId);
        return "redirect:/";
    }

    @PostMapping("/{noticeId}/attend/user/{userId}")
    public String attendMeeting(@PathVariable Long noticeId, @PathVariable String userId, @RequestParam String attention){
        noticeService.voteMeeting(noticeId, userId, attention);
        return "redirect:/";
    }
}
