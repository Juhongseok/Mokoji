package com.jhs.mokoji.service;

import com.jhs.mokoji.controller.request.NoticeCreateRequest;
import com.jhs.mokoji.controller.request.NoticeUpdateRequest;
import com.jhs.mokoji.domain.Attendance;
import com.jhs.mokoji.domain.Group;
import com.jhs.mokoji.domain.Notice;
import com.jhs.mokoji.domain.User;
import com.jhs.mokoji.repository.GroupRepository;
import com.jhs.mokoji.repository.NoticeRepository;
import com.jhs.mokoji.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class NoticeService {

    private final UserRepository userRepository;
    private final GroupRepository groupRepository;
    private final NoticeRepository noticeRepository;

    public void create(NoticeCreateRequest request, Long groupId) {
        Group group = groupRepository.getReferenceById(groupId);
        noticeRepository.save(request.toEntityWithFk(group));
    }

    public void update(Long noticeId, NoticeUpdateRequest request) {
        Notice notice = findNotice(noticeId);
        notice.update(request.getTitle(), request.getMeetingTime(), request.getLocation());
    }

    public void delete(Long noticeId) {
        noticeRepository.deleteById(noticeId);
    }

    public void voteMeeting(Long noticeId, String userId, String attention) {
        Notice notice = findNotice(noticeId);
        User user = userRepository.getReferenceById(userId);
        notice.addAttendance(new Attendance(user, notice, attention));
    }

    private Notice findNotice(Long noticeId) {
        return noticeRepository.findById(noticeId)
                .orElseThrow(IllegalArgumentException::new);
    }
}
