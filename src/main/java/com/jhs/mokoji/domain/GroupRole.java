package com.jhs.mokoji.domain;

public enum GroupRole {
    ROLE_REGULAR, ROLE_ASSOCIATE, ROLE_UN_APPROVED;

    public boolean isNewMember() {
        return this.equals(GroupRole.ROLE_UN_APPROVED);
    }
}
