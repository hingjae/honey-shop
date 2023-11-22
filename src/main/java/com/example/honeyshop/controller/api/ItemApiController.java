package com.example.honeyshop.controller.api;

import com.example.honeyshop.dto.item.SimpleItemResponse;
import com.example.honeyshop.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.data.domain.Sort.Direction.*;

@RequiredArgsConstructor
@RequestMapping("/api/items")
@RestController
public class ItemApiController {

    private final ItemService itemService;

    @GetMapping
    public Page<SimpleItemResponse> getItemPage(String searchParam, @PageableDefault(size = 10, page = 0, sort = "createdDate", direction = DESC) Pageable pageable) {
        return itemService.getItemsPage(searchParam, pageable);
    }

}
