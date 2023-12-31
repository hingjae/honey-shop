package com.example.honeyshop.dto.item;

import com.example.honeyshop.entity.Item;
import com.example.honeyshop.entity.ItemImage;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class DetailItemResponse {
    private final Long id;
    private final String name;
    private final int price;
    private final int stock;
    private final String description;
    private final List<String> imagePathList;
    private final boolean isSoldOut;

    @Builder
    private DetailItemResponse(Long id, String name, int price, int stock, String description, List<String> imagePathList, boolean isSoldOut) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.description = description;
        this.imagePathList = imagePathList;
        this.isSoldOut = isSoldOut;
    }

    public static DetailItemResponse from(Item entity) {

        boolean isSoldOut = entity.getStock() == 0;

        return DetailItemResponse.builder()
                .id(entity.getId())
                .name(entity.getName())
                .price(entity.getPrice())
                .stock(entity.getStock())
                .description(entity.getDescription())
                .imagePathList(
                        entity.getItemImages().stream()
                                .map(ItemImage::getImagePath)
                                .collect(Collectors.toList())
                )
                .isSoldOut(isSoldOut)
                .build();
    }
}
