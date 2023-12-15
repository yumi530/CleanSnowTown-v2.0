package com.project.cleansnowtown.domain.member;

import com.project.cleansnowtown.config.oauth.OAuthAttributes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.User;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    User findByUsername(String username);

    Optional<Member> findByEmail(String email);

    Optional<Object> findByRefreshToken(String refreshToken);

    Member findByOauthTypeAndOauthId(OauthType oauthType, String id);
}
