package com.project.cleansnowtown.domain.member;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum MemberStatus {
    MEMBER_STATUS_WAIT("대기"),
    MEMBER_STATUS_ING("활성"),
    MEMBER_STATUS_STOP("휴면"),
    MEMBER_STATUS_WITHDRAW("탈퇴");

    private final String text;
}
