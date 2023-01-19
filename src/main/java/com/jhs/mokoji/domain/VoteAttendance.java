package com.jhs.mokoji.domain;

public enum VoteAttendance {
    ATTENDANCE("Y"),
    NON_ATTENDANCE("N");

    private final String vote;

    VoteAttendance(String vote) {
        this.vote = vote;
    }

    public static VoteAttendance of(String value) {
        return value.equals("Y") ? ATTENDANCE : NON_ATTENDANCE;
    }
}
