package com.project.cleansnowtown.config.oauth;

import com.project.cleansnowtown.config.oauth.provider.KakaoOAuth2UserInfo;
import com.project.cleansnowtown.config.oauth.provider.NaverOAuth2UserInfo;
import com.project.cleansnowtown.config.oauth.provider.OAuth2UserInfo;
import com.project.cleansnowtown.domain.member.Member;
import com.project.cleansnowtown.domain.member.MemberRole;
import com.project.cleansnowtown.domain.member.OauthType;
import lombok.*;

import java.util.Map;
import java.util.UUID;

@Builder(access = AccessLevel.PRIVATE)
public record OAuthAttributes(String nameAttributeKey,
                              OAuth2UserInfo oauth2UserInfo) {

    public static OAuthAttributes of(OauthType oauthType, String userNameAttributeName,
                                     Map<String, Object> attributes) {

        switch (oauthType) {
            case KAKAO:
                return ofKakao(userNameAttributeName, attributes);
            case NAVER:
                return ofNaver(userNameAttributeName, attributes);
            default:
                throw new RuntimeException();
        }
    }

    private static OAuthAttributes ofKakao(String userNameAttributeName, Map<String, Object> attributes) {

        return OAuthAttributes.builder()
                .nameAttributeKey(userNameAttributeName)
                .oauth2UserInfo(new KakaoOAuth2UserInfo(attributes))
                .build();
    }

    public static OAuthAttributes ofNaver(String userNameAttributeName, Map<String, Object> attributes) {

        return OAuthAttributes.builder()
                .nameAttributeKey(userNameAttributeName)
                .oauth2UserInfo(new NaverOAuth2UserInfo(attributes))
                .build();
    }

    Member toEntity(OauthType oauthType, OAuth2UserInfo oauth2UserInfo) {

        return Member.builder()
                .oauthType(oauthType)
                .oauthId(oauth2UserInfo.getId())
                .email(UUID.randomUUID() + "@socialUser.com")
                .nickname(oauth2UserInfo.getNickname())
                .memberRole(MemberRole.GUEST)
                .build();
    }
}