package mate.academy.repositiry.order;

import java.util.Optional;
import mate.academy.model.Order;
import mate.academy.model.OrderItem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
    Page<OrderItem> findByOrder(Order order, Pageable pageable);

    Optional<OrderItem> findByIdAndOrder(Long itemId, Order order);
}
