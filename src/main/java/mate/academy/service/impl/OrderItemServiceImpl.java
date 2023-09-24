package mate.academy.service.impl;

import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import mate.academy.dto.order.OrderItemResponseDto;
import mate.academy.exception.EntityNotFoundException;
import mate.academy.mapper.OrderItemMapper;
import mate.academy.model.Order;
import mate.academy.model.OrderItem;
import mate.academy.repositiry.order.OrderItemRepository;
import mate.academy.repositiry.order.OrderRepository;
import mate.academy.service.OrderItemService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderItemServiceImpl implements OrderItemService {
    private final OrderItemRepository orderItemRepository;
    private final OrderItemMapper orderItemMapper;
    private final OrderRepository orderRepository;

    @Override
    public List<OrderItemResponseDto> getOrderItemsByOrderId(Long orderId, Pageable pageable) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() ->
                        new EntityNotFoundException("Order with id " + orderId + " not found!"));

        Page<OrderItem> orderItemsPage = orderItemRepository.findByOrder(order, pageable);
        return orderItemsPage.stream()
                .map(orderItemMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public OrderItemResponseDto getOrderItemByOrderIdAndItemId(Long orderId, Long itemId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(()
                        -> new EntityNotFoundException("Order with id " + orderId + " not found!"));

        OrderItem orderItem = orderItemRepository.findByIdAndOrder(itemId, order)
                .orElseThrow(()
                        -> new EntityNotFoundException("OrderItem with id " + itemId
                        + " not found in order with id " + orderId));

        return orderItemMapper.toDto(orderItem);
    }
}
