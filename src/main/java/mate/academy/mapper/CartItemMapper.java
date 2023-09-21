package mate.academy.mapper;

import mate.academy.config.MapperConfig;
import mate.academy.dto.cart.item.CartItemDto;
import mate.academy.model.CartItem;
import org.mapstruct.Mapper;

@Mapper(config = MapperConfig.class)
public interface CartItemMapper {
    CartItemDto toDto(CartItem cartItem);
}
