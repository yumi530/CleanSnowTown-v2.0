package com.project.cleansnowtown.domain.member;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum MemberRole {
    USER("사용자"),
    OAUTH_USER("간편로그인 사용자"),
    PICK_UP_MANAGER("수거 담당자"),
    ADMIN("관리자"),
    GUEST("손님");
    private final String text;
}