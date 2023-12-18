package com.project.cleansnowtown.api.controller.admin.member;

import com.project.cleansnowtown.api.service.admin.member.AdminMemberService;
import com.project.cleansnowtown.dto.member.MemberResponse;
import com.project.cleansnowtown.exception.member.MemberErrorCode;
import com.project.cleansnowtown.exception.member.MemberNotFoundException;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("api/v1/admin/member")
@RequiredArgsConstructor
public class AdminMemberController {

    private final AdminMemberService adminMemberService;

    @GetMapping
    public ResponseEntity<List<MemberResponse>> getAllMembers() {

        return ResponseEntity.ok(adminMemberService.getAllMembers());
    }

    @GetMapping("/{id}")
    public EntityModel<MemberResponse> getMemberInfo(@PathVariable Long id) {

        MemberResponse member = Optional.ofNullable(id)
                .map(adminMemberService::getMemberInfo)
                .orElseThrow(() -> new MemberNotFoundException(MemberErrorCode.MEMBER_NOT_FOUND));

        EntityModel entityModel = EntityModel.of(member);
        WebMvcLinkBuilder linkTo = linkTo(methodOn(this.getClass()).getAllMembers());
        entityModel.add(linkTo.withRel("all-members"));

        return entityModel;
    }
}
