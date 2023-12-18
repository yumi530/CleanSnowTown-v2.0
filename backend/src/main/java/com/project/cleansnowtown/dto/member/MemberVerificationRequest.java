package com.project.cleansnowtown.dto.member;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
@Schema(description = "사용자 인증 메세지 전송을 위한 DTO")
public class MemberVerificationRequest {

    @Schema(description = "인증 코드를 수신하는 사용자의 휴대전화번호 입니다.")
    String phone;

    @Schema(description = "인증코드 입니다.")
    @NotBlank(message = "인증코드는 필수입니다.")
    String code;
}
