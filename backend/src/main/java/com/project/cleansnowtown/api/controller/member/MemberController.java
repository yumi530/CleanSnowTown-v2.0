package com.project.cleansnowtown.api.controller.member;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.project.cleansnowtown.api.service.member.MemberService;
import com.project.cleansnowtown.config.oauth.CustomOAuth2UserService;
import com.project.cleansnowtown.dto.member.*;
import com.project.cleansnowtown.dto.sms.MessageRequest;

import com.project.cleansnowtown.dto.sms.SmsService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/member")
public class MemberController {

    private final MemberService memberService;
    private final SmsService smsService;

    @PostMapping("/signup")
    public ResponseEntity<Void> signup(@Valid @RequestBody MemberCreateRequest request) {

        memberService.signup(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/sendMessage")
    public ResponseEntity<String> sendSms(@Valid @RequestBody MessageRequest request)
            throws UnsupportedEncodingException, URISyntaxException, NoSuchAlgorithmException, InvalidKeyException, JsonProcessingException {

        String to = smsService.sendSms(request.getTo());
        return ResponseEntity.status(HttpStatus.OK).body(to);
    }

    @PostMapping("/verify")
    public ResponseEntity<String> verifyCode(@Valid @RequestBody MemberVerificationRequest request) {

        String result = smsService.verifyCode(request.getPhone(), request.getCode());
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @PutMapping
    public ResponseEntity<MemberUpdateResponse> update(
            @Valid @RequestBody MemberUpdateRequest request) {

        MemberUpdateResponse response = memberService.updateMember(request);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping
    public ResponseEntity<MemberResponse> info() {

        MemberResponse response = memberService.getMemberInfo();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping
    public ResponseEntity delete() {

        memberService.deleteMember();
        return ResponseEntity.noContent().build();
    }
}
