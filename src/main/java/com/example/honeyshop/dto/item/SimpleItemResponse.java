package com.example.honeyshop.dto.item;

import com.example.honeyshop.entity.Item;
import com.example.honeyshop.entity.ItemImage;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class SimpleItemResponse {
    private Long id;
    private String imagePath;
    private String name;
    private int price;
    private int stock;
    private LocalDateTime createdDate;

    @Builder
    private SimpleItemResponse(Long id, String imagePath, String name, int price, int stock, LocalDateTime createdDate) {
        this.id = id;
        this.imagePath = imagePath;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.createdDate = createdDate;
    }

    public static SimpleItemResponse from(Item entity) {
        return SimpleItemResponse.builder()
                .id(entity.getId())
                .imagePath(
                        entity.getItemImages().stream()
                                .filter(ItemImage::isThumbnail)
                                .map(ItemImage::getImagePath)
                                .findFirst()
                                .orElse(null)
                )
                .name(entity.getName())
                .price(entity.getPrice())
                .stock(entity.getStock())
                .createdDate(entity.getCreatedDate())
                .build();
    }
}
