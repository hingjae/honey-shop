package com.example.honeyshop.entity;

import lombok.*;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class CartItem extends BaseTimeEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cart_id")
    private Cart cart;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    @Setter @Column(nullable = false) private int quantity;
    @Column(nullable = false) private int price;

    @Builder
    private CartItem(Long id, Cart cart, Item item, int quantity, int price) {
        this.id = id;
        this.cart = cart;
        this.item = item;
        this.quantity = quantity;
        this.price = price;
    }

    public boolean isExist(Item item) {
        return this.item.equals(item);
    }
}
