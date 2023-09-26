package mate.academy.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;
import lombok.Data;
import org.hibernate.validator.constraints.ISBN;

@Data
public class CreateBookRequestDto {
    @Size(min = 1, max = 255)
    @NotNull
    private String title;

    @NotNull
    @Size(min = 1, max = 255)
    private String author;

    @ISBN
    private String isbn;

    @NotNull
    @Positive
    private BigDecimal price;

    private String description;

    private String coverImage;
}
