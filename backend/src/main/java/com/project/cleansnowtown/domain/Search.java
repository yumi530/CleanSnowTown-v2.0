package com.project.cleansnowtown.domain;

import jakarta.persistence.Embeddable;
import lombok.Getter;

@Embeddable
@Getter
public class Search {
    private String searchCondition;
    private String searchKeyword;
}
