package com.project.cleansnowtown.domain.order_item;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.project.cleansnowtown.domain.BaseEntity;
import com.project.cleansnowtown.domain.item.Item;
import com.project.cleansnowtown.domain.order.Order;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import static jakarta.persistence.FetchType.LAZY;

@Entity
@Getter
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderItem extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_item_id")
    private Long id;
    @Column(name = "order_count")
    private int count;
    private int totalPrice;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    @JsonIgnore
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    @Builder
    private OrderItem(int count, int totalPrice, Item item, Order order){
        this.count = count;
        this.totalPrice = totalPrice;
        this.item = item;
        this.order = order;
    }
}
