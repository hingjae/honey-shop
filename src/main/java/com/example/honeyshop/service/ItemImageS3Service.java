package com.example.honeyshop.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.example.honeyshop.dto.itemimage.S3InfoDtoDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class ItemImageS3Service {

    private final AmazonS3 amazonS3;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;
    @Transactional
    public S3InfoDtoDto uploadImage(MultipartFile image) {
        try {
            String imageName = image.getOriginalFilename();
            String randomId = UUID.randomUUID().toString();

            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentLength(image.getSize());
            metadata.setContentType(image.getContentType());

            amazonS3.putObject(bucket, randomId, image.getInputStream(), metadata);
            return S3InfoDtoDto.builder()
                    .imagePath(amazonS3.getUrl(bucket, randomId).toString())
                    .imageName(imageName)
                    .build();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

}
