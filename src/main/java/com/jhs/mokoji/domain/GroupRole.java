package com.jhs.mokoji.domain;

public enum GroupRole {
    ROLE_REGULAR, ROLE_ASSOCIATE, ROLE_UN_APPROVED, ROLE_MANAGER;

    public boolean isNewMember() {
        return this.equals(GroupRole.ROLE_UN_APPROVED);
    }
}
