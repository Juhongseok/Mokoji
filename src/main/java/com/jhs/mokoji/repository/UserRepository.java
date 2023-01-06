package com.jhs.mokoji.repository;

import com.jhs.mokoji.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
}
