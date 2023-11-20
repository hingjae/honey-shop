package com.example.honeyshop.dto.itemimage;

import com.example.honeyshop.entity.Item;
import com.example.honeyshop.entity.ItemImage;
import lombok.Builder;
import lombok.Getter;

@Getter
public class S3InfoDtoDto {
    private final String imagePath;
    private final String imageName;

    @Builder
    private S3InfoDtoDto(String imagePath, String imageName, boolean isThumbnail) {
        this.imagePath = imagePath;
        this.imageName = imageName;
    }

    public ItemImage toEntity(Item item, boolean isThumbnail) {
        return ItemImage.builder()
                    .item(item)
                    .imagePath(imagePath)
                    .imageName(imageName)
                    .isThumbnail(isThumbnail)
                    .build();
    }

}
