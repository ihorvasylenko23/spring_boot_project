package mate.academy.repositiry.shopping.cart;

import java.util.Optional;
import mate.academy.model.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Long> {
    @Query("SELECT s FROM ShoppingCart s "
            + "LEFT JOIN FETCH s.cartItems c "
            + "LEFT JOIN FETCH c.book WHERE s.user.id = :userId")
    Optional<ShoppingCart> findByUserId(@Param("userId") Long userId);
}
