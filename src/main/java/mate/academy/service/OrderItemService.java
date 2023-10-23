package mate.academy.service;

import java.util.List;
import mate.academy.dto.order.OrderItemResponseDto;
import org.springframework.data.domain.Pageable;

public interface OrderItemService {
    List<OrderItemResponseDto> getOrderItemsByOrderId(Long orderId, Pageable pageable);

    OrderItemResponseDto getOrderItemByOrderIdAndItemId(Long orderId, Long itemId);
}
