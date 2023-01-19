package com.jhs.mokoji.init;

import com.jhs.mokoji.auth.CustomUser;
import com.jhs.mokoji.controller.request.GroupCreateRequest;
import com.jhs.mokoji.controller.request.NoticeCreateRequest;
import com.jhs.mokoji.controller.request.UserSignUpRequest;
import com.jhs.mokoji.domain.*;
import com.jhs.mokoji.service.GroupService;
import com.jhs.mokoji.service.NoticeService;
import com.jhs.mokoji.service.UserGroupService;
import com.jhs.mokoji.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class InitData {

    private final UserService userService;
    private final GroupService groupService;
    private final UserGroupService userGroupService;
    private final NoticeService noticeService;

    private List<User> users = new ArrayList<>();
    private List<Group> groups = new ArrayList<>();
    private List<Notice> notices = new ArrayList<>();

    @PostConstruct
    public void init() {
        initData();
        saveUsers();
        saveGroups();
        saveNotices();
        voteMeeting();
    }

    private void initData() {
        for (int i = 0; i < 20; i++) {
            users.add(new User("user" + i, "user" + i + "!", "user" + i, Role.ROLE_USER));
        }
        for (int i = 0; i < 4; i++) {
            groups.add(new Group("group" + i, "this is group" + i, "dobong", 50, "climb"));
        }
        for (int i = 0; i < groups.size(); i++) {
            notices.add(new Notice("notice" + i, LocalDate.now(), "dobong", groups.get(i)));
        }
    }

    private void saveUsers() {
        for (int i = 0; i < 20; i++) {
            User user = users.get(i);
            userService.save(new UserSignUpRequest(user.getId(), user.getPassword(), user.getName()));
        }
    }

    private void saveGroups() {
        for (int i = 0; i < groups.size(); i++) {
            User user = users.get(i);
            Group group = groups.get(i);
            groupService.add(
                    new GroupCreateRequest(group.getName(), group.getExplanation(), group.getArea(), group.getNumberOfParticipation(), group.getInterests()),
                    new CustomUser(user)
            );
        }
    }

    private void saveNotices() {
        for (int i = 0; i < notices.size(); i++) {
            Notice notice = notices.get(i);
            noticeService.create(new NoticeCreateRequest(notice.getTitle(), notice.getMeetingTime().toString(), notice.getLocation()), (long) (i+1));
        }
    }

    private void voteMeeting() {
        for (int i = 4; i < users.size(); i++) {
            User user = users.get(i);
            noticeService.voteMeeting((long) (i % 4 + 1), user.getId(), i % 2 == 0 ? "Y" : "N");
        }
    }
}