package com.example.honeyshop.service;

import com.example.honeyshop.dto.cartitem.SaveCartItemRequest;
import com.example.honeyshop.entity.Cart;
import com.example.honeyshop.entity.CartItem;
import com.example.honeyshop.entity.Item;
import com.example.honeyshop.entity.user.User;
import com.example.honeyshop.repository.CartItemRepository;
import com.example.honeyshop.repository.CartRepository;
import com.example.honeyshop.repository.ItemRepository;
import com.example.honeyshop.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CartItemService {

    private final CartItemRepository cartItemRepository;
    private final CartRepository cartRepository;
    private final UserRepository userRepository;
    private final ItemRepository itemRepository;
    @Transactional
    public void saveCartItem(SaveCartItemRequest request, String userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(EntityNotFoundException::new);

        Cart cart = user.getCart();

        if (cart == null) {
            cart = saveCart(user);
            user.setCart(cart);
        }

        Item item = itemRepository.findById(request.getItemId())
                .orElseThrow(EntityNotFoundException::new);

        List<CartItem> cartItems = cart.getCartItems();
        CartItem targetCartItem = getTargetCartItem(cartItems, item);

        if (targetCartItem != null) {
            updateCartItemQuantity(request, cartItems, item);
        } else {
            saveNewCartItem(request, cart);
        }
    }

    private CartItem getTargetCartItem(List<CartItem> cartItems, Item item) {
        return cartItems.stream()
                .filter(cartItem -> cartItem.isExist(item))
                .findFirst()
                .orElse(null);
    }

    private void saveNewCartItem(SaveCartItemRequest request, Cart cart) {
        cartItemRepository.save(
                CartItem.builder()
                        .cart(cart)
                        .item(
                                itemRepository.findById(request.getItemId())
                                        .orElseThrow(EntityNotFoundException::new)
                        )
                        .quantity(request.getQuantity())
                        .price(request.getPrice())
                        .build()
        );
    }

    private static void updateCartItemQuantity(SaveCartItemRequest request, List<CartItem> cartItems, Item item) {
        CartItem targetCartItem = cartItems.stream()
                .filter(cartItem -> cartItem.getItem().equals(item))
                .findFirst()
                .orElseThrow(IllegalStateException::new);
        int targetCartItemQuantity = targetCartItem.getQuantity();
        targetCartItem.setQuantity(targetCartItemQuantity + request.getQuantity());
    }

    private Cart saveCart(User user) {
        return cartRepository.save(
                Cart.builder()
                        .user(user)
                        .build()
        );
    }
}
