package com.example.honeyshop.service;

import com.example.honeyshop.dto.item.DetailItemResponse;
import com.example.honeyshop.dto.item.SimpleItemResponse;
import com.example.honeyshop.dto.item.UploadItemRequest;
import com.example.honeyshop.entity.Item;
import com.example.honeyshop.repository.CategoryRepository;
import com.example.honeyshop.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.persistence.EntityNotFoundException;

@RequiredArgsConstructor
@Service
public class ItemService {
    private final ItemRepository itemRepository;
    private final CategoryRepository categoryRepository;
    private final ItemImageService itemImageService;
    @Transactional
    public void saveItem(UploadItemRequest request) {
        Item item = itemRepository.save(
                Item.builder()
                        .category(
                                categoryRepository.findById(request.getCategoryId())
                                        .orElseThrow(EntityNotFoundException::new)
                        )
                        .name(request.getName())
                        .price(request.getPrice())
                        .stock(request.getStock())
                        .description(request.getDescription())
                        .build()
        );
        if (!request.getImages().isEmpty()) {
            itemImageService.saveItemImages(item, request.getImages());
        }
    }

    @Transactional(readOnly = true)
    public Page<SimpleItemResponse> getItemsPage(String searchParam, Pageable pageable) {
        if (StringUtils.hasText(searchParam)) {
            return itemRepository.findByNameContaining(searchParam, pageable)
                    .map(SimpleItemResponse::from);
        }
        return itemRepository.findAll(pageable)
                    .map(SimpleItemResponse::from);
    }

    @Transactional(readOnly = true)
    public DetailItemResponse getItemPage(Long itemId) {
        return itemRepository.findById(itemId)
                .map(DetailItemResponse::from)
                .orElseThrow(EntityNotFoundException::new);
    }
}
