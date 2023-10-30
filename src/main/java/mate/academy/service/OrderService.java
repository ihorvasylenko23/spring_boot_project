package mate.academy.service;

import java.util.List;
import mate.academy.dto.order.CreateOrderRequestDto;
import mate.academy.dto.order.OrderItemResponseDto;
import mate.academy.dto.order.OrderResponseDto;
import mate.academy.dto.order.UpdateStatusRequestDto;
import mate.academy.model.User;
import org.springframework.data.domain.Pageable;

public interface OrderService {
    List<OrderResponseDto> getAll(Pageable pageable, Long userId);

    OrderResponseDto save(CreateOrderRequestDto requestDto, User user);

    OrderResponseDto updateOrderStatus(Long id, UpdateStatusRequestDto requestDto);

    List<OrderItemResponseDto> getAllByOrderId(Long orderId, Pageable pageable, Long userId);

    OrderItemResponseDto getOrderItemById(Long orderId, Long itemId);
}
