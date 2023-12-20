package com.project.cleansnowtown.api.service.member;

import com.project.cleansnowtown.domain.Address;
import com.project.cleansnowtown.domain.member.Member;
import com.project.cleansnowtown.domain.member.MemberRepository;
import com.project.cleansnowtown.domain.member.MemberStatus;
import com.project.cleansnowtown.dto.member.*;
import com.project.cleansnowtown.exception.member.MemberCustomException;
import com.project.cleansnowtown.exception.member.MemberErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public void signup(MemberCreateRequest request) {

        memberRepository.findByEmail(request.getEmail())
                .ifPresent(email -> {
                    throw new MemberCustomException(MemberErrorCode.DUPLICATE_EMAIL);
                });

        Member member = Member.builder()
                .email(request.getEmail())
                .username(request.getUsername())
                .password(request.getPassword())
                .phone(request.getPhone())
                .district(request.getDistrict())
                .memberRole(request.getMemberRole())
                .memberStatus(MemberStatus.MEMBER_STATUS_ING)
                .address(Address.builder()
                        .city(request.getCity())
                        .street(request.getStreet())
                        .zipCode(request.getZipCode())
                        .build())
                .build();

        member.passwordEncode(passwordEncoder);
        memberRepository.save(member);
    }

    public MemberUpdateResponse updateMember(MemberUpdateRequest request) {

        Member member = findMemberFromAuthentication();
        member.update(request, passwordEncoder);
        return MemberUpdateResponse.of(member);
    }

    public MemberResponse getMemberInfo() {

        Member member = findMemberFromAuthentication();
        return MemberResponse.of(member);
    }

    public void deleteMember() {

        Member member = findMemberFromAuthentication();
        memberRepository.deleteByEmail(member.getEmail());
    }

    public boolean isAuthorized(String emaill) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication != null && authentication.isAuthenticated() &&
                authentication.getName().equals(emaill);
    }

    private Member findMemberFromAuthentication() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        return Optional.ofNullable(authentication)
                .filter(auth -> auth.getPrincipal() instanceof User)
                .map(auth -> (User) auth.getPrincipal())
                .map(User::getUsername)
                .flatMap(memberRepository::findByEmail)
                .orElseThrow(() -> new MemberCustomException(MemberErrorCode.MEMBER_NOT_FOUND));
    }
}
