package mate.academy.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.util.HashSet;
import java.util.Set;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Entity
@Data
@Table(name = "shopping_carts")
@SQLDelete(sql = "UPDATE shopping_carts SET is_deleted = true WHERE id=?")
@Where(clause = "is_deleted=false")
public class ShoppingCart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private User user;

    @OneToMany(mappedBy = "shoppingCart", cascade = CascadeType.ALL)
    @Fetch(FetchMode.JOIN)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Set<CartItem> cartItems = new HashSet<>();

    @Column(nullable = false)
    private boolean isDeleted = false;
}
