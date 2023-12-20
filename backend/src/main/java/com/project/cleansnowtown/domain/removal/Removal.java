package com.project.cleansnowtown.domain.removal;

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
@Table(name = "removal")
@Getter
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class Removal extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "removal_id")
    private Long id;

    @Embedded
    private Address address;

    @Enumerated(EnumType.STRING)
    @Column(name = "removal_status")
    private RemovalStatus removalStatus;

    @JsonIgnore
    @OneToOne(mappedBy = "removal", fetch = LAZY)
    private Payment payment;

    @Builder
    private Removal(Address address, RemovalStatus removalStatus, Payment payment){
        this.address = address;
        this.removalStatus = removalStatus;
        this.payment = payment;
    }
}
