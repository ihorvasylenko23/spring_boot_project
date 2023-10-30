package mate.academy.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.Set;
import lombok.Data;
import org.hibernate.validator.constraints.ISBN;

@Data
public class CreateBookRequestDto {
    @Size(min = 1, max = 255)
    @NotBlank
    private String title;

    @NotBlank
    @Size(min = 1, max = 255)
    private String author;

    @ISBN
    private String isbn;

    @NotNull
    @Positive
    private BigDecimal price;

    @NotBlank
    private String description;

    @NotBlank
    private String coverImage;
    private Set<Long> categoryIds;
}
