package com.example.honeyshop.dto.cartitem;

import com.example.honeyshop.entity.CartItem;
import com.example.honeyshop.entity.Item;
import com.example.honeyshop.entity.ItemImage;
import lombok.Builder;
import lombok.Getter;

@Getter
public class CartItemResponse {
    private final Long cartItemId;
    private final Long itemId;
    private final String itemName;
    private final String imagePath;
    private final int price;
    private final int quantity;
    private final int totalPrice;

    @Builder
    private CartItemResponse(Long cartItemId, Long itemId, String itemName, String imagePath, int price, int quantity, int totalPrice) {
        this.cartItemId = cartItemId;
        this.itemId = itemId;
        this.itemName = itemName;
        this.imagePath = imagePath;
        this.price = price;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
    }


    public static CartItemResponse from(CartItem entity) {
        Item item = entity.getItem();
        return CartItemResponse.builder()
                .cartItemId(entity.getId())
                .itemId(item.getId())
                .itemName(item.getName())
                .imagePath(
                        item.getItemImages().stream()
                                .filter(ItemImage::isThumbnail)
                                .map(ItemImage::getImagePath)
                                .findFirst()
                                .orElseGet(() -> null)
                )
                .quantity(entity.getQuantity())
                .price(entity.getPrice())
                .totalPrice(entity.getPrice() * entity.getQuantity())
                .build();
    }
}
