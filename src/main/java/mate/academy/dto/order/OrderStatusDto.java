package mate.academy.dto.order;

import lombok.Data;
import mate.academy.model.enums.Status;

@Data
public class OrderStatusDto {
    private Status status;
}
