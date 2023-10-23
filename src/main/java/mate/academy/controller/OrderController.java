package mate.academy.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.dto.order.OrderCreateRequestDto;
import mate.academy.dto.order.OrderItemResponseDto;
import mate.academy.dto.order.OrderResponseDto;
import mate.academy.dto.order.OrderStatusDto;
import mate.academy.model.User;
import mate.academy.service.OrderItemService;
import mate.academy.service.OrderService;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@Tag(name = "Order management", description = "Endpoints for managing orders")
@RestController
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;
    private final OrderItemService orderItemService;

    @PostMapping
    @Operation(summary = "Create a new order", description = "Create a new order for a user")
    public OrderResponseDto createOrder(@RequestBody OrderCreateRequestDto requestDto,
                                        Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        return orderService.createOrder(user, requestDto);
    }

    @GetMapping
    @Operation(summary = "Get all orders", description = "Get list of all orders for a user")
    public List<OrderResponseDto> getAll(Authentication authentication, Pageable pageable) {
        User user = (User) authentication.getPrincipal();
        return orderService.getOrdersByUser(user, pageable);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PatchMapping("/{id}")
    @Operation(summary = "Update order status", description = "Update status of an order by id")
    public void updateOrderStatus(@PathVariable Long id, @RequestBody OrderStatusDto statusDto) {
        orderService.updateOrderStatus(id, statusDto.getStatus());
    }

    @GetMapping("/{orderId}/items")
    @Operation(summary = "Get all order items",
            description = "Get list of all items for an order by order id")
    public List<OrderItemResponseDto> getAllOrderItemsByOrderId(
            @PathVariable Long orderId, Pageable pageable) {
        return orderItemService.getOrderItemsByOrderId(orderId, pageable);
    }

    @GetMapping("/{orderId}/items/{itemId}")
    @Operation(summary = "Get order item",
            description = "Get a specific item of an order by order id and item id")
    public OrderItemResponseDto getOrderItemByOrderIdAndItemId(
            @PathVariable Long orderId, @PathVariable Long itemId) {
        return orderItemService.getOrderItemByOrderIdAndItemId(orderId, itemId);
    }
}
