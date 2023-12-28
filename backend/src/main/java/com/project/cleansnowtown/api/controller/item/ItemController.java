package com.project.cleansnowtown.api.controller.item;

import com.project.cleansnowtown.api.controller.order.ItemService;
import com.project.cleansnowtown.dto.item.ItemResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/item")
public class ItemController {

    private final ItemService itemService;

    @GetMapping
    public ResponseEntity<List<ItemResponse>> getAllItems() {

        return ResponseEntity.ok(itemService.getAllItems());
    }
}
