package com.example.honeyshop.dto.item;

import com.example.honeyshop.entity.Item;
import lombok.Builder;
import lombok.Getter;

@Getter
public class SimpleItemResponse {
    private Long id;
    private String name;
    private int price;
    private int stock;

    @Builder
    private SimpleItemResponse(Long id, String name, int price, int stock) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

    public static SimpleItemResponse from(Item entity) {
        return SimpleItemResponse.builder()
                .id(entity.getId())
                .name(entity.getName())
                .price(entity.getPrice())
                .stock(entity.getStock())
                .build();
    }
}
