package mate.academy.repositiry.order;

import java.util.List;
import java.util.Optional;
import mate.academy.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findAllByUserId(Long userId);

    Optional<Object> findByUserId(Long userId);
}
