package com.jhs.mokoji.repository;

import com.jhs.mokoji.domain.Notice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoticeRepository extends JpaRepository<Notice, Long> {
}
