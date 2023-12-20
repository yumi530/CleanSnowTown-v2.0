package com.project.cleansnowtown.dto.member;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.project.cleansnowtown.domain.member.District;
import com.project.cleansnowtown.domain.member.Member;
import com.project.cleansnowtown.domain.member.MemberRole;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Schema(description = "사용자 정보 조회 DTO")
public class MemberResponse {

    @Schema(description = "사용자의 고유번호(조회용) 입니다.")
    private Long id;

    @Schema(description = "사용자의 이메일(아이디) 입니다.")
    private String email;

    @Schema(description = "간편 로그인 사용자의 Oauth 아이디 입니다.")
    private String oauthId;

    @Schema(description = "사용자의 이름 입니다.")
    private String username;

    @Schema(description = "사용자의 비밀번호 입니다.")
    @JsonIgnore
    private String password;

    @Schema(description = "사용자의 휴대전화번호 입니다.")
    private String phone;

    @Schema(description = "사용자의 행정구역 입니다.")
    private District district;

    @Schema(description = "사용자의 거주 도시(시/군/구) 입니다.")
    private String city;

    @Schema(description = "사용자의 상세 주소 입니다.")
    private String street;

    @Schema(description = "사용자의 우편번호 입니다.")
    private String zipCode;

    @Schema(description = "사용자의 포인트 입니다.")
    private int point;

    @Schema(description = "사용자의 권한 입니다.")
    private MemberRole memberRole;

    @Builder
    public MemberResponse(Long id, String email, String oauthId, String username, String password, String phone, District district, String city, String street, String zipCode, int point, MemberRole memberRole){

        this.id = id;
        this.email = email;
        this.oauthId = oauthId;
        this.username = username;
        this.password = password;
        this.phone = phone;
        this.district = district;
        this.city = city;
        this.street = street;
        this.zipCode = zipCode;
        this.point = point;
        this.memberRole = memberRole;
    }

    @Builder
    public static MemberResponse of(Member member) {

        return MemberResponse.builder()
                .id(member.getId())
                .email(member.getEmail())
                .oauthId(member.getOauthId())
                .username(member.getUsername())
                .password(member.getPassword())
                .phone(member.getPhone())
                .district(member.getDistrict())
                .city(member.getAddress().getCity())
                .street(member.getAddress().getStreet())
                .zipCode(member.getAddress().getZipCode())
                .point(member.getPoint())
                .memberRole(member.getMemberRole())
                .build();
    }
}
