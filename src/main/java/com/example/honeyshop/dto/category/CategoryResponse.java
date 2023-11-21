package com.example.honeyshop.dto.category;

import com.example.honeyshop.entity.Category;
import lombok.Builder;
import lombok.Getter;

@Getter
public class CategoryResponse {
    private final Long id;
    private final String title;

    @Builder
    private CategoryResponse(Long id, String title) {
        this.id = id;
        this.title = title;
    }

    public static CategoryResponse from(Category entity) {
        return CategoryResponse.builder()
                .id(entity.getId())
                .title(entity.getTitle())
                .build();
    }
}
