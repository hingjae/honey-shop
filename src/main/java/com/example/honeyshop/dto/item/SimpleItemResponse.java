package com.example.honeyshop.dto.item;

import com.example.honeyshop.entity.Item;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class SimpleItemResponse {
    private Long id;
    private String name;
    private int price;
    private int stock;
    private LocalDateTime createdDate;

    @Builder
    private SimpleItemResponse(Long id, String name, int price, int stock, LocalDateTime createdDate) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.createdDate = createdDate;
    }

    public static SimpleItemResponse from(Item entity) {
        return SimpleItemResponse.builder()
                .id(entity.getId())
                .name(entity.getName())
                .price(entity.getPrice())
                .stock(entity.getStock())
                .createdDate(entity.getCreatedDate())
                .build();
    }
}
