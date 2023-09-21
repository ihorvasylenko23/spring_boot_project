package mate.academy.service;

import mate.academy.dto.shopping.cart.AddToCartRequestDto;
import mate.academy.dto.shopping.cart.ShoppingCartDto;

public interface ShoppingCartService {

    ShoppingCartDto addToCart(AddToCartRequestDto requestDto, Long userId);

    ShoppingCartDto getCartByUserId(Long userId);

    ShoppingCartDto updateCartItem(Long cartItemId, int quantity);

    void deleteCartItem(Long cartItemId);
}
