package com.example.honeyshop.entity;

import lombok.*;

import javax.persistence.*;

@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Item extends BaseTimeEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    @Column(length = 255, nullable = false) private String name;
    @Column(nullable = false) private int price;
    @Column(nullable = false) private int stock;
    @Column(length = 1000, nullable = true) private String description;

    @Builder
    private Item(Category category, String name, int price, int stock, String description) {
        this.category = category;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.description = description;
    }
}
