package com.project.cleansnowtown.dto.item;

import com.project.cleansnowtown.domain.Search;
import com.project.cleansnowtown.domain.item.Category;
import com.project.cleansnowtown.domain.item.Item;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class ItemCreateRequest {

    private String name;
    private int price;
    private String size;
    private String type;
    private Category category;

    public Item toEntity (){
        return Item.builder()
                .name(name)
                .price(price)
                .size(size)
                .type(type)
                .category(category)
                .build();
    }


}
