package com.project.cleansnowtown.domain.pickup;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.project.cleansnowtown.domain.Address;
import com.project.cleansnowtown.domain.BaseEntity;
import com.project.cleansnowtown.domain.payment.Payment;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import static jakarta.persistence.FetchType.LAZY;

@Entity
@Getter
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class Pickup extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pickup_id")
    private Long id;

    @Embedded
    private Address address;

    @Enumerated(EnumType.STRING)
    private PickupStatus pickupStatus;

    @JsonIgnore
    @OneToOne(mappedBy = "pickup", fetch = LAZY)
    private Payment payment;

    @Builder
    private Pickup(Address address, PickupStatus pickupStatus, Payment payment){
        this.address = address;
        this.pickupStatus = pickupStatus;
        this.payment = payment;
    }
}
