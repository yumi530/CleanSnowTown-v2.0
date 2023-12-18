package com.project.cleansnowtown.domain.payment;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum PaymentStatus {
    PAYMENT_STATUS_WAIT("결제 대기"),
    PAYMENT_STATUS_CANCEL("결제 취소"),
    PAYMENT_STATUS_COMPLETE("결제 완료"),
    PAYMENT_STATUS_REFUND("결제 환불");

    private final String text;
}
