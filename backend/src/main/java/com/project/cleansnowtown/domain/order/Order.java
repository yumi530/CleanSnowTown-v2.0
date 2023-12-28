package com.project.cleansnowtown.domain.order;

import com.project.cleansnowtown.domain.Address;
import com.project.cleansnowtown.domain.BaseEntity;
import com.project.cleansnowtown.domain.Search;
import com.project.cleansnowtown.domain.payment.Payment;
import com.project.cleansnowtown.domain.member.Member;
import com.project.cleansnowtown.domain.order_item.OrderItem;
import com.project.cleansnowtown.domain.removal.Removal;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.FetchType.LAZY;

@Entity
@Table(name = "orders")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Order extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long id;

    private LocalDateTime orderDate;

    @Embedded
    private Address address;

    @Embedded
    private Search search;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> orderItems = new ArrayList<>();

    @OneToOne(fetch = LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "removal_id")
    private Removal removal;

    @OneToOne(fetch = LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "payment_id")
    private Payment payment;



    @Builder
    private Order(LocalDateTime orderDate, Address address, Search search,Member member, Removal removal, Payment payment, List<OrderItem> orderItems){
        this.orderDate = orderDate;
        this.address = address;
        this.search = search;
        this.member = member;
        this.removal = removal;
        this.payment = payment;
        this.orderItems = orderItems;
    }


    public static Order createOrder(Member member, Removal removal, OrderItem... orderItems) {

        return Order.builder()
                .member(member)
                .removal(removal)
                .orderItems(List.of(orderItems))
                .build();
    }
}