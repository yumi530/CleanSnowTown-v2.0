package com.project.cleansnowtown.domain.item;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Category {
    SMALL_HOME_APPLIANCES("소형 폐가전"),
    FURNITURE("가구 용품"),
    KITCHEN_SUPPLIES("주방 용품"),
    BATHROOM_SUPPLIES("욕실 용품"),
    ETC_SUPPLIES("기타 용품");

    private final String text;
}
