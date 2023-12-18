package com.project.cleansnowtown.config.oauth;

import com.project.cleansnowtown.domain.member.MemberRepository;
import com.project.cleansnowtown.domain.member.OauthType;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import com.project.cleansnowtown.domain.member.Member;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {
    private final MemberRepository memberRepository;

    private static final String NAVER = "naver";

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

        log.info("CustomOAuth2UserService.loadUser() 실행");

        OAuth2UserService<OAuth2UserRequest, OAuth2User> delegate = new DefaultOAuth2UserService();
        OAuth2User oAuth2User = delegate.loadUser(userRequest);

        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        OauthType oauthType = getOauthType(registrationId);
        String userNameAttributeName = userRequest.getClientRegistration()
                .getProviderDetails().getUserInfoEndpoint().getUserNameAttributeName();
        Map<String, Object> attributes = oAuth2User.getAttributes();

        OAuthAttributes extractAttributes = OAuthAttributes.of(oauthType, userNameAttributeName, attributes);

        Member createdMember = getMember(extractAttributes, oauthType);

        return new CustomOAuth2User(
                Collections.singleton(new SimpleGrantedAuthority(createdMember.getMemberRole().name())),
                attributes,
                extractAttributes.nameAttributeKey(),
                createdMember.getEmail(),
                createdMember.getMemberRole()
        );
    }

    private Member getMember(OAuthAttributes attributes, OauthType oauthType) {

        Member findMember = memberRepository.findByOauthTypeAndOauthId(oauthType, attributes.oauth2UserInfo().getId()).orElse(null);
        if(findMember == null) {
            return saveMember(attributes, oauthType);
        }
        return findMember;
    }

    private Member saveMember(OAuthAttributes attributes, OauthType oauthType) {

        Member createdMember = attributes.toEntity(oauthType, attributes.oauth2UserInfo());
        return memberRepository.save(createdMember);
    }

    private OauthType getOauthType(String registrationId) {

        if(NAVER.equals(registrationId)) {
            return OauthType.NAVER;
        }
            return OauthType.KAKAO;
    }
}
