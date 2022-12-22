package com.jhs.mokoji.domain;

import com.jhs.mokoji.domain.baseentity.TimeInfo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Persistable;

import javax.persistence.*;

import static javax.persistence.EnumType.STRING;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "USERS")
public class User extends TimeInfo implements Persistable<String> {

    @Id
    @Column(nullable = false, unique = true)
    private String id;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String name;

    @Enumerated(STRING)
    @Column(nullable = false)
    private Role role;

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public boolean isNew() {
        return super.createdAt == null;
    }
}
