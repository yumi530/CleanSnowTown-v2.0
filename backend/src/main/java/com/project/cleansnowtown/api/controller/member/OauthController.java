//package com.project.cleansnowtown.api.controller.member;
//
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//@Controller
//public class OauthController {
//
//    @GetMapping("/oauth2/authorization/kakao")
//    public String handleKakaoCallback(@RequestParam("code") String code,
//                                      @RequestParam("state") String state,
//                                      Model model) {
//        // Kakao OAuth 코드 콜백 처리 로직
//        String accessToken = kakaoAPI.getAccessToken(code);
//
//        // 처리 후 리다이렉션할 페이지를 반환합니다.
//        return "redirect:/api/v1/member/login/oauth/code/kakao";
//    }
//
//    @GetMapping("/oauth2/authorization/naver")
//    public String handleNaverCallback(@RequestParam("code") String code,
//                                      @RequestParam("state") String state,
//                                      Model model) {
//        // Kakao OAuth 코드 콜백 처리 로직
//
//        // 처리 후 리다이렉션할 페이지를 반환합니다.
//        return "redirect:/api/v1/member/login/oauth/code/naver";
//    }
//}
