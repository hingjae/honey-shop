package com.example.honeyshop.dto.cartitem;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter @Setter
public class DeleteCartItemRequest {
    private Long cartItemId;
}
