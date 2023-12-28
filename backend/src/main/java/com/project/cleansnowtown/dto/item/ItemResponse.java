package com.project.cleansnowtown.dto.item;

import com.project.cleansnowtown.domain.Search;
import com.project.cleansnowtown.domain.item.Category;
import com.project.cleansnowtown.domain.item.Item;
import lombok.Builder;
import lombok.Getter;

@Getter
public class ItemResponse {

    private Long id;
    private String name;
    private int price;
    private String size;
    private String type;
    private Search search;
    private Category category;

    @Builder
    private ItemResponse (Long id, String name, int price, String size, String type, Search search, Category category){
        this.id = id;
        this.name = name;
        this.price = price;
        this.size = size;
        this.type = type;
        this.search = search;
        this.category = category;
    }

    public static ItemResponse of(Item item) {
        return ItemResponse.builder()
                .id(item.getId())
                .name(item.getName())
                .price(item.getPrice())
                .size(item.getSize())
                .type(item.getType())
                .search(item.getSearch())
                .category(item.getCategory())
                .build();
    }
}
