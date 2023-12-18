package com.project.cleansnowtown.dto.member;

import com.project.cleansnowtown.domain.member.District;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Schema(description = "사용자 정보 업데이트를 위한 DTO")
public class MemberUpdateRequest {

    @Schema(description = "사용자 비밀번호를 입력합니다.")
    @NotBlank(message = "비밀번호는 필수입니다.")
    @Size(min = 8, max = 16, message = "비밀번호는 8자리 이상 16자리 이하로 입력해주십시오.")
    private String password;

    @Schema(description = "사용자 휴대전화번호를 입력합니다.")
    @NotBlank(message = "휴대전화번호는 필수입니다.")
    private String phone;

    @Schema(description = "사용자 행정구역을 입력합니다.")
    @NotBlank(message = "행정구역 필수입니다.")
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
}
