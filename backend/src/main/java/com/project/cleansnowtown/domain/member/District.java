package com.project.cleansnowtown.domain.member;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum District {
    AREA_1("행정1동"),
    AREA_2("행정2동"),
    AREA_3("행정3동"),
    AREA_4("행정4동"),
    AREA_5("행정5동"),
    AREA_6("행정6동");

    private final String text;

}
