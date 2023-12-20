package com.project.cleansnowtown.domain.pickup;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum RemovalStatus {

    REMOVAL_STATUS_WAIT("수거 대기"),
    REMOVAL_STATUS_COMPLETE("수거 완료"),
    REMOVAL_STATUS_REJECT("수거 불가");

    private final String text;
}
