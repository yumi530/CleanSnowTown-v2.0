package com.project.cleansnowtown.api.controller.order;

import com.project.cleansnowtown.domain.item.Item;
import com.project.cleansnowtown.domain.item.ItemRepository;
import com.project.cleansnowtown.dto.item.ItemResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class ItemService {

    private final ItemRepository itemRepository;

    public List<ItemResponse> getAllItems() {

        List<Item> items = itemRepository.findAll();
        return items.stream()
                .map(ItemResponse::of)
                .collect(Collectors.toList());
    }

    public ItemResponse getItem(ItemResponse response) {

        Item item = itemRepository.findById(response.getId()).orElseThrow(() -> new RuntimeException("item not found"));
        return ItemResponse.of(item);
    }
}
