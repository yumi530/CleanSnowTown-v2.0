package com.project.cleansnowtown.dto.member;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Getter
@Schema(description = "로그인 응답 DTO")
public class MemberLoginResponse {

	@Schema(description = "인증 방식 입니다.")
	private String grantType;

	@Schema(description = "액세스 토큰 입니다.")
	private String accessToken;

	@Schema(description = "리프레시 토큰 입니다.")
	private String refreshToken;

	@Builder
	private MemberLoginResponse(String grantType, String accessToken, String refreshToken) {

		this.grantType = grantType;
		this.accessToken = accessToken;
		this.refreshToken = refreshToken;
	}
}
