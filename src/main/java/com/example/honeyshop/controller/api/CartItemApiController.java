package com.example.honeyshop.controller.api;

import com.example.honeyshop.dto.cartitem.DeleteCartItemRequest;
import com.example.honeyshop.service.CartItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/api/cartItems")
@RestController
public class CartItemApiController {

    private final CartItemService cartItemService;

    @DeleteMapping
    public ResponseEntity<Void> deleteCartItem(@RequestBody DeleteCartItemRequest request) {
        cartItemService.deleteCartItem(request.getCartItemId());
        return ResponseEntity.noContent().build();
    }
}
