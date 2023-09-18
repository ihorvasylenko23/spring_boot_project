package mate.academy.dto.book;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;
import lombok.Data;

@Data
public class CreateBookRequestDto {
    @Size(min = 1, max = 255)
    @NotNull
    private String title;
    @NotNull
    @Size(min = 1, max = 255)
    private String author;
    @NotNull
    private String isbn;
    @NotNull
    @Positive
    private BigDecimal price;
    @NotNull
    private String description;
    @NotNull
    private String coverImage;
}
