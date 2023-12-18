package com.project.cleansnowtown.dto.member;

import com.project.cleansnowtown.domain.member.District;
import com.project.cleansnowtown.domain.member.MemberRole;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Schema(description = "회원 가입을 위한 DTO")
public class MemberCreateRequest {
    @Schema(description = "간편 로그인 사용자의 Oauth 아이디 입니다.")
    private String oauthId;

    @Schema(description = "사용자 이메일(아이디)을 입혁합니다.")
    @NotBlank(message = "이메일(ID)은 필수입니다.")
    @Email(message = "유효한 이메일 주소를 입력하세요.")
    private String email;

    @Schema(description = "사용자 이름을 입혁합니다.")
    @NotBlank(message = "이름은 필수입니다.")
    private String username;

    @Schema(description = "사용자 비밀번호를 입력합니다.")
    @NotBlank(message = "비밀번호는 필수입니다.")
    @Size(min = 8, message = "비밀번호는 최소 8자리 이상 입력해주십시오.")
    private String password;

    @Schema(description = "사용자 휴대전화번호를 입력합니다.")
    @NotBlank(message = "휴대전화번호는 필수입니다.")
    private String phone;

    @Schema(description = "사용자 행정구역을 입력합니다.")
    private District district;

    @Schema(description = "사용자 거주 도시(시/군/구)를 입력합니다.")
    @NotBlank(message = "주소는 필수입니다.")
    private String city;

    @Schema(description = "사용자 상세 주소를 입력합니다.")
    @NotBlank(message = "주소는 필수입니다.")
    private String street;

    @Schema(description = "사용자 우편번호를 입력합니다.")
    @NotBlank(message = "주소는 필수입니다.")
    private String zipCode;

    @Schema(description = "사용자별로 권한을 부여합니다.")
    private MemberRole memberRole;
}