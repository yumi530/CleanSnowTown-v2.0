package com.project.cleansnowtown.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.cleansnowtown.api.controller.member.MemberController;
import com.project.cleansnowtown.api.service.member.MemberService;
import com.project.cleansnowtown.config.TestSecurityConfig;
import com.project.cleansnowtown.domain.member.District;
import com.project.cleansnowtown.domain.member.MemberRole;
import com.project.cleansnowtown.dto.member.MemberCreateRequest;
import com.project.cleansnowtown.dto.member.MemberResponse;
import com.project.cleansnowtown.dto.member.MemberUpdateRequest;
import com.project.cleansnowtown.dto.sms.SmsService;
import com.project.cleansnowtown.jwt.service.JwtService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ActiveProfiles("test")
@Import(TestSecurityConfig.class)
@WebMvcTest(controllers = {MemberController.class})
class MemberControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private JwtService jwtService;

    @MockBean
    private SmsService smsService;

    @MockBean
    private MemberService memberService;

    @DisplayName("신규 회원 등록시 이메일(아이디)은 필수값이다.")
    @Test
    void loginWithoutEmail() throws Exception {
        // given
        MemberCreateRequest request = MemberCreateRequest.builder()
                .username("김회원")
                .password("user1234")
                .phone("010111110004")
                .district(District.AREA_2)
                .city("seoul")
                .street("seongsu")
                .zipCode("00111")
                .memberRole(MemberRole.USER)
                .build();

        // when// then
        mockMvc.perform(
                        post("/api/v1/member/signup")
                                .content(objectMapper.writeValueAsString(request))
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status").value("400"));
    }

    @DisplayName("회원정보를 수정한다.")
    @Test
    void updateMember() throws Exception {
        // given
        MemberUpdateRequest request = MemberUpdateRequest.builder()
                .password("12345678")
                .phone("01000010004")
                .district(District.AREA_1)
                .city("seoul")
                .street("nowon")
                .zipCode("00111")
                .build();
        // when // then
        mockMvc.perform(
                        put("/api/v1/member")
                                .content(objectMapper.writeValueAsString(request))
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isOk());
    }

    @DisplayName("마이페이지를 조회힌다.")
    @Test
    void getInfo() throws Exception {
        // given
        MemberResponse response = MemberResponse.builder().build();
        given(memberService.getMemberInfo()).willReturn(response);

        // when // then
        mockMvc.perform(
                        get("/api/v1/member")
                )
                .andDo(print())
                .andExpect(status().isOk());
    }
}
