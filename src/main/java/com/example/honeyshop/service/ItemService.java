package com.example.honeyshop.service;

import com.example.honeyshop.dto.item.SimpleItemResponse;
import com.example.honeyshop.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class ItemService {
    private final ItemRepository itemRepository;

    @Transactional(readOnly = true)
    public Page<SimpleItemResponse> getItemPage(Pageable pageable) {
        return itemRepository.findAll(pageable)
                    .map(SimpleItemResponse::from);
    }
}
