package com.project.cleansnowtown.config.oauth;

import com.project.cleansnowtown.domain.member.Member;
import com.project.cleansnowtown.domain.member.MemberRole;
import com.project.cleansnowtown.domain.member.OauthType;
import lombok.*;

import java.util.Map;
import java.util.UUID;

@ToString
@Builder(access = AccessLevel.PRIVATE)
@Getter
@RequiredArgsConstructor
public class OAuthAttributes {
    private final String nameAttributeKey;
    private final OAuth2UserInfo oauth2UserInfo;

    public static OAuthAttributes of(OauthType oauthType, String attributeKey,
                                       Map<String, Object> attributes) {

        switch (oauthType) {
            case KAKAO:
                return ofKakao("email", attributes);
            case NAVER:
                return ofNaver("id", attributes);
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
                .username(oauth2UserInfo.getUsername())
                .memberRole(MemberRole.GUEST)
                .build();
    }
}
