package com.example.honeyshop.controller;

import com.example.honeyshop.dto.cartitem.SaveCartItemRequest;
import com.example.honeyshop.dto.security.UserPrincipal;
import com.example.honeyshop.service.CartItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@RequestMapping
@RequiredArgsConstructor
@Controller
public class CartItemController {

    private final CartItemService cartItemService;

    @PostMapping("/cartItem")
    public String saveCartItem(SaveCartItemRequest request, @AuthenticationPrincipal UserPrincipal userPrincipal, RedirectAttributes redirectAttributes) {
        cartItemService.saveCartItem(request, userPrincipal.getUsername());
        redirectAttributes.addAttribute("itemId", request.getItemId());
        return "redirect:/items/{itemId}";
    }
}
