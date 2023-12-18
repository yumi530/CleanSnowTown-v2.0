package com.project.cleansnowtown.dto.member;

import com.project.cleansnowtown.domain.member.District;
import com.project.cleansnowtown.domain.member.Member;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@Schema(description = "사용자 정보 업데이트 응답 DTO")
public class MemberUpdateResponse {

    @Schema(description = "사용자의 비밀번호 입니다.")
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

    @Builder
    private MemberUpdateResponse(String password, String phone, District district, String city, String street, String zipCode) {
        this.password = password;
        this.phone = phone;
        this.district = district;
        this.city = city;
        this.street = street;
        this.zipCode = zipCode;
    }

    public static MemberUpdateResponse of(Member member) {

        return MemberUpdateResponse.builder()
                .password(member.getPassword())
                .phone(member.getPhone())
                .district(member.getDistrict())
                .city(member.getAddress().getCity())
                .street(member.getAddress().getStreet())
                .zipCode(member.getAddress().getZipCode())
                .build();
    }
}
