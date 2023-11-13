package com.example.honeyshop.service;

import com.example.honeyshop.dto.item.ItemListResponse;
import com.example.honeyshop.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ItemService {
    private final ItemRepository itemRepository;

    public Page<ItemListResponse> getItemPage(Pageable pageable) {
        return null;
    }
}
