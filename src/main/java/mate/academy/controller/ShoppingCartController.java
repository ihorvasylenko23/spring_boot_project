package mate.academy.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import mate.academy.dto.cart.item.CartItemDto;
import mate.academy.dto.shopping.cart.AddToCartRequestDto;
import mate.academy.dto.shopping.cart.ShoppingCartDto;
import mate.academy.model.User;
import mate.academy.service.ShoppingCartService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Cart management", description = "Endpoints for managing carts")
@RequiredArgsConstructor
@RestController
@RequestMapping("/cart")
public class ShoppingCartController {
    private final ShoppingCartService shoppingCartService;

    @GetMapping
    @Operation(summary = "Get user's cart",
            description = "Retrieve shopping cart for the authenticated user")
    @PreAuthorize("hasRole('USER')")
    public ShoppingCartDto getCartByUserId(Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        return shoppingCartService.getCartByUserId(user.getId());
    }

    @PostMapping
    @Operation(summary = "Add to cart",
            description = "Add an item to the authenticated user's shopping cart")
    @PreAuthorize("hasRole('USER')")
    public ShoppingCartDto addToCart(@RequestBody AddToCartRequestDto requestDto,
                                     Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        return shoppingCartService.addToCart(requestDto, user.getId());
    }

    @PutMapping("/cart-items/{cartItemId}")
    @Operation(summary = "Update cart item",
            description = "Update the quantity of an item in the cart")
    @PreAuthorize("hasRole('USER')")
    public ShoppingCartDto updateCartItem(@PathVariable Long cartItemId,
                                          @RequestBody CartItemDto cartItemDto,
                                          Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        return shoppingCartService
                .updateCartItem(user.getId(),
                        cartItemId,
                        cartItemDto.getQuantity());
    }

    @DeleteMapping("/cart-items/{cartItemId}")
    @Operation(summary = "Delete cart item",
            description = "Remove an item from the shopping cart")
    @PreAuthorize("hasRole('USER')")
    public void deleteCartItem(@PathVariable Long cartItemId,
                               Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        shoppingCartService.deleteCartItem(cartItemId, user.getId());
    }
}
