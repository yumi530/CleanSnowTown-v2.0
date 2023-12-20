package com.project.cleansnowtown.dto.sms;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class MessageRequest {

    @Schema(description = "인증 코드를 발송할 사용자의 휴대전화번호 입니다.")
    @NotBlank(message = "휴대전화번호는 필수입니다.")
    private String to;

    @Schema(description = "인증코드 입니다.")
    private String content;
}

