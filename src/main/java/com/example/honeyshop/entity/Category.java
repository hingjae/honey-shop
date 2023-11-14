package com.example.honeyshop.entity;

import lombok.*;

import javax.persistence.*;

@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50, nullable = false) private String title;

    @Builder
    private Category(Long id, String title) {
        this.id = id;
        this.title = title;
    }
}
