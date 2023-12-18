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

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;
    @Column(name = "username")
    private String name;
    private String password;
    private String email;
    private int point;
    private String oauthId;
    private String oauthProvider;

    @Embedded
    private Address address;

    @Embedded
    private Search search;

    @Enumerated(EnumType.STRING)
    private MemberRole memberRole;
    @Enumerated(EnumType.STRING)
    private MemberStatus memberStatus;
    @Enumerated(EnumType.STRING)
    private District district;

    @JsonIgnore
    @OneToMany(mappedBy = "member")
    private List<Order> orders = new ArrayList<>();

    @Builder
    private Member(String name, String password, String email, String oauthId, String oauthProvider,int point, Address address,
                   Search search, MemberRole memberRole, MemberStatus memberStatus,  District district, List<Order> orders) {
        this.name = name;
        this.password = password;
        this.email = email;
        this.oauthId = oauthId;
        this.oauthProvider = oauthProvider;
        this.point = point;
        this.address = address;
        this.search = search;
        this.memberRole = memberRole;
        this.memberStatus = memberStatus;
        this.district = district;
        this.orders = orders;
    }
}
