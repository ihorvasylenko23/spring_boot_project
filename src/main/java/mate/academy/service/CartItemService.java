package mate.academy.service;

import mate.academy.dto.cart.item.CartItemDto;

public interface CartItemService {
    CartItemDto findById(Long id);
}
