package com.example.honeyshop.dto.cart;

import com.example.honeyshop.dto.cartitem.CartItemResponse;
import com.example.honeyshop.entity.Cart;
import com.example.honeyshop.entity.CartItem;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class CartResponse {
    private final Long cartId;
    private final List<CartItemResponse> cartItems;
    private final int cartItemsTotalPriceSum;

    @Builder
    public CartResponse(Long cartId, List<CartItemResponse> cartItems, int cartItemsTotalPriceSum) {
        this.cartId = cartId;
        this.cartItems = cartItems;
        this.cartItemsTotalPriceSum = cartItemsTotalPriceSum;
    }

    public static CartResponse from(Cart cart, List<CartItem> cartItems) {

        List<CartItemResponse> cartItemResponseList = cartItems.stream()
                .map(CartItemResponse::from)
                .collect(Collectors.toList());

        return CartResponse.builder()
                .cartId(cart.getId())
                .cartItems(cartItemResponseList)
                .cartItemsTotalPriceSum(
                        cartItemResponseList.stream()
                                .mapToInt(CartItemResponse::getTotalPrice)
                                .sum()
                )
                .build();
    }
}
