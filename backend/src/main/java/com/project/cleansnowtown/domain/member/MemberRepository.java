package com.project.cleansnowtown.domain.member;

import com.project.cleansnowtown.config.oauth.OAuthAttributes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.User;

import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByEmail(String email);

    Optional<Member> findByRefreshToken(String refreshToken);

    Optional<Member> findByOauthTypeAndOauthId(OauthType oauthType, String id);

    Optional<Member> findByPhone(String phone);

    void deleteByEmail(String email);

    List<Member> findAllByMemberRole(MemberRole user);

    Optional<Member> findByOauthId(String oauthId);
}