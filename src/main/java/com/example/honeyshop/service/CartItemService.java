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
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RequiredArgsConstructor
@Service
public class CartItemService {

    private final CartItemRepository cartItemRepository;
    private final UserRepository userRepository;
    private final ItemRepository itemRepository;
    @Transactional
    public void saveCartItem(SaveCartItemRequest request, String userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(EntityNotFoundException::new);

        Cart cart = user.getCart();

        Item item = itemRepository.findById(request.getItemId())
                .orElseThrow(EntityNotFoundException::new);

        List<CartItem> cartItems = cart.getCartItems();

        //기존 장바구니에 이미 같은 물건이 담겨있는지 확인
        //새로운 물건추가 또는 업데이트
        List<Item> items = cartItems.stream()
                .map(CartItem::getItem)
                .collect(Collectors.toList());

        if (items.contains(item)) {
            updateCartItemQuantity(request, cartItems, item);
        } else {
            saveNewCartItem(request, cart);
        }
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
        CartItem selectedItem = cartItems.stream()
                .filter(cartItem -> cartItem.getItem().equals(item))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);

        selectedItem.increaseQuantity(request.getQuantity());
    }

    @Transactional
    public void deleteCartItem(Long cartItemId) {
        cartItemRepository.deleteById(cartItemId);
    }
}
