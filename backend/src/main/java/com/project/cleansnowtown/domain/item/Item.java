package com.project.cleansnowtown.domain.item;

import com.project.cleansnowtown.domain.BaseEntity;
import com.project.cleansnowtown.domain.Search;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Item extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_id")
    private Long id;
    @Column(name = "item_name")
    private String name;
    private int price;
    private String size;
    private String type;

    @Embedded
    private Search search;

    @Enumerated(EnumType.STRING)
    private Category category;

    @Builder
    private Item(String name, int price, String size, String type, Search search, Category category){
        this.name = name;
        this.price = price;
        this.size = size;
        this.type = type;
        this.search = search;
        this.category = category;
    }

}
