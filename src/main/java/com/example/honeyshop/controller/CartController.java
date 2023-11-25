package com.example.honeyshop.controller;

import com.example.honeyshop.dto.cart.CartResponse;
import com.example.honeyshop.dto.cartitem.CartItemResponse;
import com.example.honeyshop.dto.security.UserPrincipal;
import com.example.honeyshop.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@RequestMapping("/cart")
@RequiredArgsConstructor
@Controller
public class CartController {

    private final CartService cartService;

    @GetMapping
    public String CartPage(@AuthenticationPrincipal UserPrincipal userPrincipal, Model model) {
        CartResponse cart = cartService.getCart(userPrincipal.getUsername());
        model.addAttribute("cartItems", cart.getCartItems());
        model.addAttribute("cartItemsTotalPriceSum", cart.getCartItemsTotalPriceSum());
        return "cart";
    }
}
