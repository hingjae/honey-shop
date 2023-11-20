package com.example.honeyshop.entity;

import lombok.*;

import javax.persistence.*;

@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class ItemImage extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;
    @Column(nullable = false, length = 255) private String imagePath;
    @Column(nullable = false, length = 255) private String imageName;
    @Column(nullable = false) private boolean isThumbnail;

    @Builder
    private ItemImage(Item item, String imagePath, String imageName, boolean isThumbnail) {
        this.item = item;
        this.imagePath = imagePath;
        this.imageName = imageName;
        this.isThumbnail = isThumbnail;
    }
}
