package com.example.honeyshop.dto.cartitem;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
public class SaveCartItemRequest {
    private Long itemId;
    private int price;
    private int quantity;
}
