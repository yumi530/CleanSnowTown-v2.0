package com.project.cleansnowtown.exception.member;

import lombok.Getter;

@Getter
public class MemberNotFoundException extends RuntimeException{

    private final MemberErrorCode errorCode;

    public MemberNotFoundException(MemberErrorCode errorCode) {
        super(errorCode.getDescription());
        this.errorCode = errorCode;
    }
}
