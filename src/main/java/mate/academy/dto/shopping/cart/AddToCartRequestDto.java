package mate.academy.dto.shopping.cart;

import lombok.Data;

@Data
public class AddToCartRequestDto {
    private Long bookId;
    private int quantity;
}
