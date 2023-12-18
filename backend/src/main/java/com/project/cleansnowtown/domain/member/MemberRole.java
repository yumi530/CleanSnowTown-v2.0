package com.project.cleansnowtown.domain.member;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum MemberRole {
    USER("사용자"),
    PICK_UP_MANAGER("수거 담당자"),
    ADMIN("관리자");

    private final String text;
}