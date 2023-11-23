package com.example.honeyshop.service;

import com.example.honeyshop.dto.cart.CartResponse;
import com.example.honeyshop.entity.Cart;
import com.example.honeyshop.entity.user.User;
import com.example.honeyshop.repository.CartRepository;
import com.example.honeyshop.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@Slf4j
@RequiredArgsConstructor
@Service
public class CartService {

    private final CartRepository cartRepository;
    private final UserRepository userRepository;
    @Transactional
    public CartResponse getCart(String username) {
        User user = userRepository.findById(username)
                .orElseThrow(EntityNotFoundException::new);
        Cart cart = user.getCart();
        if (cart == null) {
            cart = saveCart(user);
            user.setCart(cart);
        }
        return CartResponse.from(cart);
    }

    private Cart saveCart(User user) {
        return cartRepository.save(
                Cart.builder()
                        .user(user)
                        .build()
        );
    }

}
