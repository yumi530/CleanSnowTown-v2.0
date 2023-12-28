package com.project.cleansnowtown.domain.member;

import com.project.cleansnowtown.domain.Address;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static com.project.cleansnowtown.domain.member.MemberRole.*;
import static org.assertj.core.api.AssertionsForClassTypes.tuple;
import static org.assertj.core.api.Assertions.assertThat;



@ActiveProfiles("test")
@SpringBootTest
public class MemberRepositoryTest {

    @Autowired
    private MemberRepository memberRepository;

    @DisplayName("회원을 권환 별로 조회한다.")
    @Test
    void findAllByMemberRole() {
        //given
        Member member1 = Member.builder()
                .email("user1@test.com")
                .username("김회원")
                .password("user1234")
                .phone("01000001111")
                .district(District.AREA_1)
                .memberRole(MemberRole.USER)
                .memberStatus(MemberStatus.MEMBER_STATUS_ING)
                .address(Address.builder()
                        .city("seoul")
                        .street("seongsu")
                        .zipCode("00001")
                        .build())
        .build();
       Member member2 =  Member.builder()
                .email("user2@test.com")
                .username("이회원")
                .password("user12345")
                .phone("01000002222")
                .district(District.AREA_2)
                .memberRole(MemberRole.USER)
                .memberStatus(MemberStatus.MEMBER_STATUS_ING)
                .address(Address.builder()
                        .city("seoul")
                        .street("seongsu")
                        .zipCode("00001")
                        .build())
                .build();
        Member member3 = Member.builder()
                .email("admin1@test.com")
                .username("박관리")
                .password("admin1234")
                .phone("01000003333")
                .district(District.AREA_1)
                .memberRole(ADMIN)
                .memberStatus(MemberStatus.MEMBER_STATUS_ING)
                .address(Address.builder()
                        .city("seoul")
                        .street("seongsu")
                        .zipCode("10001")
                        .build())
                .build();
        memberRepository.saveAll(List.of(member1, member2, member3));

        //when
        List<Member> members = memberRepository.findAllByMemberRole(USER);

        //then
        assertThat(members)
                .hasSize(2)
                .extracting("email", "username", "memberRole")
                .containsExactlyInAnyOrder(
                        tuple("user1@test.com", "김회원", MemberRole.USER),
                        tuple("user2@test.com", "이회원", MemberRole.USER)
                );
    }

}
