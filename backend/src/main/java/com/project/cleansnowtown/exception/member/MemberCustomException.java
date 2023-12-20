package com.project.cleansnowtown.exception.member;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class MemberCustomException extends RuntimeException {

    private final MemberErrorCode errorCode;
    private static final ObjectMapper mapper = new ObjectMapper();

    public MemberCustomException(MemberErrorCode errorCode) {
        super(errorCode.getDescription());
        this.errorCode = errorCode;
    }

}
