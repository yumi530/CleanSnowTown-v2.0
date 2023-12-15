package com.project.cleansnowtown.config.oauth;

import com.project.cleansnowtown.domain.member.MemberRole;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;

import java.util.Collection;
import java.util.Map;

@Getter
public class CustomOAuth2User extends DefaultOAuth2User {

    private String email;
    private MemberRole memberRole;

    public CustomOAuth2User(Collection<? extends GrantedAuthority> authorities,
                            Map<String, Object> attributes, String nameAttributeKey,
                            String email, MemberRole memberRole) {
        super(authorities, attributes, nameAttributeKey);
        this.email = email;
        this.memberRole = memberRole;
    }
}
