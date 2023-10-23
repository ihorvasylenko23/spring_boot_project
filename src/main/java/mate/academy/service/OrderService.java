package mate.academy.service;

import java.util.List;
import mate.academy.dto.order.OrderCreateRequestDto;
import mate.academy.dto.order.OrderResponseDto;
import mate.academy.model.User;
import mate.academy.model.enums.Status;
import org.springframework.data.domain.Pageable;

public interface OrderService {
    OrderResponseDto createOrder(User user, OrderCreateRequestDto requestDto);

    List<OrderResponseDto> getOrdersByUser(User user, Pageable pageable);

    void updateOrderStatus(Long id, Status status);
}
