package com.project.cleansnowtown.domain;

import jakarta.persistence.Embeddable;
import lombok.Getter;

@Embeddable
@Getter
public class Address {
    String city;
    String street;
    String zipCode;

}
