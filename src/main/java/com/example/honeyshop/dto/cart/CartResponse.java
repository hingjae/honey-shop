package com.example.honeyshop.dto.cart;

import com.example.honeyshop.entity.Cart;
import lombok.Builder;
import lombok.Getter;

@Getter
public class CartResponse {
    private final String userId;
    private final Long cartId;

    @Builder
    public CartResponse(String userId, Long cartId) {
        this.userId = userId;
        this.cartId = cartId;
    }

    public static CartResponse from(Cart entity) {
        return CartResponse.builder()
                .userId(entity.getUser().getId())
                .cartId(entity.getId())
                .build();
    }
}
