package com.example.honeyshop.dto.item;

import com.example.honeyshop.entity.Item;
import com.example.honeyshop.entity.ItemImage;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class SimpleItemResponse {
    private final Long id;
    private final String imagePath;
    private final String name;
    private final int price;
    private final int stock;
    private final LocalDateTime createdDate;
    private final boolean isSoldOut;

    @Builder
    private SimpleItemResponse(Long id, String imagePath, String name, int price, int stock, LocalDateTime createdDate, boolean isSoldOut) {
        this.id = id;
        this.imagePath = imagePath;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.createdDate = createdDate;
        this.isSoldOut = isSoldOut;
    }

    public static SimpleItemResponse from(Item entity) {

        boolean isSoldOut = entity.getStock() == 0;

        return SimpleItemResponse.builder()
                .id(entity.getId())
                .imagePath(
                        entity.getItemImages().stream()
                                .filter(ItemImage::isThumbnail)
                                .map(ItemImage::getImagePath)
                                .findFirst()
                                .orElseGet(() -> null)
                )
                .name(entity.getName())
                .price(entity.getPrice())
                .stock(entity.getStock())
                .createdDate(entity.getCreatedDate())
                .isSoldOut(isSoldOut)
                .build();
    }

}
