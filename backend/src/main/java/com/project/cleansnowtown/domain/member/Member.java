package com.project.cleansnowtown.domain.member;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.project.cleansnowtown.domain.Address;
import com.project.cleansnowtown.domain.BaseEntity;
import com.project.cleansnowtown.domain.Search;
import com.project.cleansnowtown.domain.order.Order;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;

import static com.project.cleansnowtown.domain.member.MemberRole.USER;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;
    private String email;
    private String username;
    private String password;
    private String phone;
    private int point;
    private String oauthId;
    private String refreshToken;

    @Embedded
    private Address address;

    @Embedded
    private Search search;

    @Enumerated(EnumType.STRING)
    private MemberRole memberRole;
    @Enumerated(EnumType.STRING)
    private OauthType oauthType;
    @Enumerated(EnumType.STRING)
    private MemberStatus memberStatus;
    @Enumerated(EnumType.STRING)
    private District district;

    @JsonIgnore
    @OneToMany(mappedBy = "member")
    private List<Order> orders = new ArrayList<>();

    @Builder
    private Member(String username, String password, String email, String phone,String oauthId, String refreshToken, OauthType oauthType, int point, Address address,
                   Search search, MemberRole memberRole, MemberStatus memberStatus,  District district, List<Order> orders) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.oauthId = oauthId;
        this.refreshToken = refreshToken;
        this.oauthType = oauthType;
        this.point = point;
        this.address = address;
        this.search = search;
        this.memberRole = memberRole;
        this.memberStatus = memberStatus;
        this.district = district;
        this.orders = orders;
    }
    // 유저 권한 설정 메소드
    public void authorizeUser() {
        this.memberRole = USER;
    }

    // 비밀번호 암호화 메소드
    public void passwordEncode(PasswordEncoder passwordEncoder) {
        this.password = passwordEncoder.encode(this.password);
    }

    public void updateRefreshToken(String updateRefreshToken) {
        this.refreshToken = updateRefreshToken;
    }

}
