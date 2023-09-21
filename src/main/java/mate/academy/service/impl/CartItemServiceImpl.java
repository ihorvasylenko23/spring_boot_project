package mate.academy.service.impl;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import mate.academy.dto.cart.item.CartItemDto;
import mate.academy.mapper.CartItemMapper;
import mate.academy.model.CartItem;
import mate.academy.repositiry.cart.item.CartItemRepository;
import mate.academy.service.CartItemService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CartItemServiceImpl implements CartItemService {
    private final CartItemRepository cartItemRepository;
    private final CartItemMapper cartItemMapper;

    @Override
    public CartItemDto findById(Long id) {
        Optional<CartItem> optionalCartItem = cartItemRepository.findById(id);
        return optionalCartItem.map(cartItemMapper::toDto).orElse(null);
    }
}
