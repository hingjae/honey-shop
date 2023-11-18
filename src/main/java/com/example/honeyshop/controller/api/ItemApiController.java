package com.example.honeyshop.controller.api;

import com.example.honeyshop.dto.item.SimpleItemResponse;
import com.example.honeyshop.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/api/items")
@RestController
public class ItemApiController {

    private final ItemService itemService;

    @GetMapping
    public Page<SimpleItemResponse> getItemPage(Pageable pageable) {
        return itemService.getItemPage(pageable);
    }

}
