package com.example.honeyshop.service;

import com.example.honeyshop.dto.itemimage.S3InfoDtoDto;
import com.example.honeyshop.entity.Item;
import com.example.honeyshop.repository.ItemImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ItemImageService {

    private final ItemImageRepository itemImageRepository;
    private final ItemImageS3Service itemImageS3Service;

    @Transactional
    public void saveItemImages(Item item, List<MultipartFile> images) {
        for (int i = 0; i < images.size(); i++) {
            S3InfoDtoDto dto = itemImageS3Service.uploadImage(images.get(i));
            if (i == 0) {
                itemImageRepository.save(dto.toEntity(item, true));
                continue;
            }
            itemImageRepository.save(dto.toEntity(item, false));
        }
    }
}
