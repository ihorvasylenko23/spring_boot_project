package mate.academy.service.impl;

import jakarta.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import mate.academy.dto.order.CreateOrderRequestDto;
import mate.academy.dto.order.OrderItemResponseDto;
import mate.academy.dto.order.OrderResponseDto;
import mate.academy.dto.order.UpdateStatusRequestDto;
import mate.academy.exception.EntityNotFoundException;
import mate.academy.mapper.OrderItemMapper;
import mate.academy.mapper.OrderMapper;
import mate.academy.model.CartItem;
import mate.academy.model.Order;
import mate.academy.model.OrderItem;
import mate.academy.model.ShoppingCart;
import mate.academy.model.User;
import mate.academy.model.enums.Status;
import mate.academy.repositiry.order.OrderItemRepository;
import mate.academy.repositiry.order.OrderRepository;
import mate.academy.service.OrderService;
import mate.academy.service.ShoppingCartService;
import mate.academy.service.UserService;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final ShoppingCartService shoppingCartService;
    private final OrderMapper orderMapper;
    private final OrderItemMapper orderItemMapper;
    private final UserService userService;

    @Transactional
    @Override
    public List<OrderResponseDto> getAll(Pageable pageable, Long userId) {
        return orderRepository.findAllByUserId(userId).stream()
                .map(orderMapper::toDto)
                .toList();
    }

    @Transactional
    @Override
    public OrderResponseDto save(CreateOrderRequestDto requestDto, User user) {
        ShoppingCart cart = shoppingCartService.getShoppingCartByUser(user);
        Order order = new Order();
        order.setUser(userService.getUser(user.getId()));
        order.setShippingAddress(requestDto.getShippingAddress());
        order.setStatus(Status.PENDING);
        order.setOrderDate(LocalDateTime.now());
        order.setTotal(cart.getCartItems().stream()
                .map(this::getPrice)
                .reduce(BigDecimal.valueOf(0), BigDecimal::add));
        orderRepository.save(order);
        OrderResponseDto dto = orderMapper.toDto(order);
        dto.setOrderItems(getOrderItemDtos(getOrderItems(cart, order)));
        return dto;
    }

    @Transactional
    @Override
    public OrderResponseDto updateOrderStatus(Long id, UpdateStatusRequestDto requestDto) {
        Order order = orderRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(
                "Can't find order by id: " + id));
        order.setStatus(requestDto.getStatus());
        return orderMapper.toDto(orderRepository.save(order));
    }

    @Transactional
    @Override
    public List<OrderItemResponseDto> getAllByOrderId(Long orderId, Pageable pageable,
                                                      Long userId) {
        return getOrderById(orderId, userId).getOrderItems().stream()
                .map(orderItemMapper::toDto)
                .toList();
    }

    @Transactional
    @Override
    public OrderItemResponseDto getOrderItemById(Long orderId, Long itemId) {
        return orderItemRepository.findById(itemId)
                .map(orderItemMapper::toDto)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Can't find order item by id: " + itemId));
    }

    private Order getOrderById(Long id, Long userId) {
        return (Order) orderRepository.findByUserId(userId)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Can't find order by id: " + id));
    }

    private Set<OrderItem> getOrderItems(ShoppingCart cart, Order order) {
        return cart.getCartItems().stream()
                .map(orderItemMapper::mapCartItemToOrderItem)
                .peek(orderItem -> saveOrderItem(orderItem, order))
                .collect(Collectors.toSet());
    }

    private List<OrderItemResponseDto> getOrderItemDtos(Set<OrderItem> orderItems) {
        return orderItems.stream()
                .map(orderItemMapper::toDto)
                .collect(Collectors.toList());
    }

    private BigDecimal getPrice(CartItem cartItem) {
        return cartItem.getBook().getPrice().multiply(BigDecimal.valueOf(cartItem.getQuantity()));
    }

    private void saveOrderItem(OrderItem orderItem, Order order) {
        orderItem.setPrice(orderItem.getPrice().multiply(
                BigDecimal.valueOf(orderItem.getQuantity())));
        orderItem.setOrder(order);
        orderItemRepository.save(orderItem);
    }
}
