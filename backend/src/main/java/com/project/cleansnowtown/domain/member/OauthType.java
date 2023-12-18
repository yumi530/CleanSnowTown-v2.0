package com.project.cleansnowtown.domain.member;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum OauthType {
    NAVER("네이버"),
    KAKAO("카카오");

    private final String text;
}
