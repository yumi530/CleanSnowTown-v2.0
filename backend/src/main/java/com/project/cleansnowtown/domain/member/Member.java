package com.project.cleansnowtown.domain.member;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.project.cleansnowtown.domain.Address;
import com.project.cleansnowtown.domain.BaseEntity;
import com.project.cleansnowtown.domain.Search;
import com.project.cleansnowtown.domain.order.Order;
import com.project.cleansnowtown.dto.member.MemberUpdateRequest;
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
    private String nickname;
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
    private Member(String username, String password, String email, String phone, String oauthId, String nickname, String refreshToken, OauthType oauthType, int point, Address address,
                   Search search, MemberRole memberRole, MemberStatus memberStatus,  District district, List<Order> orders) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.oauthId = oauthId;
        this.nickname = nickname;
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

    public void authorizeUser() {
        this.memberRole = USER;
    }

    public void passwordEncode(PasswordEncoder passwordEncoder) {
        this.password = passwordEncoder.encode(this.password);
    }

    public void updateRefreshToken(String updateRefreshToken) {
        this.refreshToken = updateRefreshToken;
    }

    public void update(MemberUpdateRequest request, PasswordEncoder passwordEncoder) {
        this.password = passwordEncoder.encode(request.getPassword());
        this.phone = request.getPhone();
        this.district = request.getDistrict();
        this.address = Address.builder()
                .city(request.getCity())
                .street(request.getStreet())
                .zipCode(request.getZipCode())
                .build();

    }
}
