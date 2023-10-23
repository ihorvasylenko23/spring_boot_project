package mate.academy.service.impl;

import jakarta.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import mate.academy.dto.order.OrderCreateRequestDto;
import mate.academy.dto.order.OrderResponseDto;
import mate.academy.exception.EntityNotFoundException;
import mate.academy.mapper.OrderMapper;
import mate.academy.model.Order;
import mate.academy.model.OrderItem;
import mate.academy.model.ShoppingCart;
import mate.academy.model.User;
import mate.academy.model.enums.Status;
import mate.academy.repositiry.order.OrderRepository;
import mate.academy.repositiry.shopping.cart.ShoppingCartRepository;
import mate.academy.service.OrderService;
import mate.academy.service.ShoppingCartService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final ShoppingCartService shoppingCartService;
    private final ShoppingCartRepository shoppingCartRepository;
    private final OrderMapper orderMapper;

    @Override
    @Transactional
    public OrderResponseDto createOrder(User user, OrderCreateRequestDto requestDto) {
        ShoppingCart shoppingCart = shoppingCartService.getShoppingCartByUser(user);
        Order order = new Order();
        order.setUser(user);
        order.setShippingAddress(requestDto.getShippingAddress());
        order.setStatus(Status.PENDING); // default
        order.setOrderDate(LocalDateTime.now());
        order.setOrderItems(shoppingCart.getCartItems().stream().map(cartItem -> {
            OrderItem orderItem = new OrderItem();
            orderItem.setBook(cartItem.getBook());
            orderItem.setQuantity(cartItem.getQuantity());
            orderItem.setPrice(cartItem.getBook().getPrice());
            orderItem.setOrder(order);
            return orderItem;
        }).collect(Collectors.toSet()));

        BigDecimal total = order.getOrderItems().stream()
                .map(item -> item.getBook()
                        .getPrice()
                        .multiply(BigDecimal.valueOf(item.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        order.setTotal(total);

        orderRepository.save(order);
        shoppingCart.getCartItems().clear();
        shoppingCartRepository.save(shoppingCart);

        return orderMapper.toDto(order);
    }

    @Override
    public List<OrderResponseDto> getOrdersByUser(User user, Pageable pageable) {
        Page<Order> ordersPage = orderRepository.findAllByUser(user, pageable);
        return ordersPage.stream()
                .map(orderMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void updateOrderStatus(Long id, Status status) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() ->
                        new EntityNotFoundException("Order with id " + id + " not found!"));
        order.setStatus(status);
        orderRepository.save(order);
    }
}
