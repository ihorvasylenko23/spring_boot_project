package mate.academy.service.impl;

import jakarta.transaction.Transactional;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import mate.academy.dto.cart.item.CartItemDto;
import mate.academy.dto.shopping.cart.AddToCartRequestDto;
import mate.academy.dto.shopping.cart.ShoppingCartDto;
import mate.academy.exception.EntityNotFoundException;
import mate.academy.mapper.CartItemMapper;
import mate.academy.mapper.ShoppingCartMapper;
import mate.academy.model.Book;
import mate.academy.model.CartItem;
import mate.academy.model.ShoppingCart;
import mate.academy.model.User;
import mate.academy.repositiry.book.BookRepository;
import mate.academy.repositiry.cart.item.CartItemRepository;
import mate.academy.repositiry.shopping.cart.ShoppingCartRepository;
import mate.academy.repositiry.user.UserRepository;
import mate.academy.service.ShoppingCartService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ShoppingCartServiceImpl implements ShoppingCartService {
    private final ShoppingCartRepository shoppingCartRepository;
    private final BookRepository bookRepository;
    private final CartItemRepository cartItemRepository;
    private final ShoppingCartMapper shoppingCartMapper;
    private final UserRepository userRepository;
    private final CartItemMapper cartItemMapper;

    @Transactional
    public ShoppingCartDto addToCart(AddToCartRequestDto requestDto, Long userId) {
        Long bookId = requestDto.getBookId();
        int quantity = requestDto.getQuantity();

        ShoppingCart cart = shoppingCartRepository.findByUserId(userId)
                .orElseGet(() -> {
                    ShoppingCart newCart = new ShoppingCart();
                    User user = userRepository.findById(userId)
                            .orElseThrow(() ->
                                    new EntityNotFoundException(
                                            "User not found with id: " + userId));
                    newCart.setUser(user);
                    return shoppingCartRepository.save(newCart);
                });

        Book book = bookRepository.findById(bookId)
                .orElseThrow(() ->
                        new EntityNotFoundException("Book not found with id: " + bookId));
        CartItem existingCartItem = cart.getCartItems().stream()
                .filter(cartItem -> cartItem.getBook().getId().equals(book.getId()))
                .findFirst()
                .orElse(null);

        if (existingCartItem != null) {
            existingCartItem.setQuantity(existingCartItem.getQuantity() + quantity);
        } else {
            CartItem newCartItem = new CartItem();
            newCartItem.setShoppingCart(cart);
            newCartItem.setBook(book);
            newCartItem.setQuantity(quantity);
            cart.getCartItems().add(newCartItem);
        }

        saveCartAndItem(cart, existingCartItem);

        return shoppingCartMapper.toDto(cart);
    }

    private void saveCartAndItem(ShoppingCart cart, CartItem existingCartItem) {
        if (existingCartItem != null) {
            cartItemRepository.save(existingCartItem);
        }
        shoppingCartRepository.save(cart);
    }

    @Override
    public ShoppingCartDto getCartByUserId(Long userId) {
        Optional<ShoppingCart> cart = shoppingCartRepository.findByUserId(userId);

        if (cart.isPresent()) {
            ShoppingCart shoppingCart = cart.get();
            List<CartItemDto> cartItemDtos = shoppingCart.getCartItems().stream()
                    .map(this::mapCartItemToDtoWithBookDetails)
                    .collect(Collectors.toList());
            ShoppingCartDto shoppingCartDto = shoppingCartMapper.toDto(shoppingCart);
            shoppingCartDto.setCartItems(cartItemDtos);

            return shoppingCartDto;
        } else {
            return null;
        }
    }

    private CartItemDto mapCartItemToDtoWithBookDetails(CartItem cartItem) {
        CartItemDto dto = cartItemMapper.toDto(cartItem);
        if (cartItem.getBook() != null) {
            dto.setBookId(cartItem.getBook().getId());
            dto.setBookTitle(cartItem.getBook().getTitle());
        }
        return dto;
    }

    @Override
    public ShoppingCartDto updateCartItem(Long cartItemId, int quantity) {
        Optional<CartItem> optionalCartItem = cartItemRepository.findById(cartItemId);
        if (optionalCartItem.isEmpty()) {
            throw new NoSuchElementException("Cart item not found with id: " + cartItemId);
        }

        CartItem cartItem = optionalCartItem.get();
        cartItem.setQuantity(quantity);
        cartItemRepository.save(cartItem);

        return shoppingCartMapper.toDto(cartItem.getShoppingCart());
    }

    @Override
    public void deleteCartItem(Long cartItemId) {
        cartItemRepository.deleteById(cartItemId);
    }
}
