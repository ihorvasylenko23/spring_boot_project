package mate.academy.dto.shopping.cart;

import java.util.List;
import lombok.Data;
import mate.academy.dto.cart.item.CartItemDto;

@Data
public class ShoppingCartDto {
    private Long id;
    private Long userId;
    private List<CartItemDto> cartItems;
}
