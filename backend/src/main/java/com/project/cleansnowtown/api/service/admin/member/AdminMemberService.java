package com.project.cleansnowtown.api.service.admin.member;

import com.project.cleansnowtown.domain.member.Member;
import com.project.cleansnowtown.domain.member.MemberRepository;
import com.project.cleansnowtown.domain.member.MemberRole;
import com.project.cleansnowtown.dto.member.MemberResponse;
import com.project.cleansnowtown.exception.member.MemberCustomException;
import com.project.cleansnowtown.exception.member.MemberErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdminMemberService {

    private final MemberRepository memberRepository;

    public List<MemberResponse> getAllMembers() {

        List<Member> members = memberRepository.findAllByMemberRole(MemberRole.USER);
        return members.stream()
                .map(MemberResponse::of)
                .collect(Collectors.toList());
    }

    public MemberResponse getMemberInfo(Long id) {

        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new MemberCustomException(MemberErrorCode.MEMBER_NOT_FOUND));
        return MemberResponse.of(member);
    }
}
