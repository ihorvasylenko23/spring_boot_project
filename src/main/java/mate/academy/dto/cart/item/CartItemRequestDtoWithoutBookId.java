package mate.academy.dto.cart.item;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class CartItemRequestDtoWithoutBookId {
    @NotNull
    @Min(1)
    private int quantity;
}
