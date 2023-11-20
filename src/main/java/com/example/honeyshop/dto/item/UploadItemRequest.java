package com.example.honeyshop.dto.item;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Getter
@Setter
public class UploadItemRequest {
    private Long categoryId;
    private String name;
    private int price;
    private int stock;
    private String description;
    private MultipartFile image1;
    private MultipartFile image2;
    private MultipartFile image3;
    private MultipartFile image4;
    private MultipartFile image5;

    public List<MultipartFile> getImages() {
        return Stream.of(image1, image2, image3, image4, image5)
                    .filter(this::isNotEmpty)
                    .collect(Collectors.toList());
    }

    private boolean isNotEmpty(MultipartFile image) {
        return !image.isEmpty();
    }
}
