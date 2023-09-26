package mate.academy.dto.book;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;
import lombok.Data;

@Data
public class CreateBookRequestDto {
    @Size(min = 1, max = 255)
    @NotBlank
    private String title;

    @NotBlank
    @Size(min = 1, max = 255)
    private String author;

    @NotBlank
    private String isbn;

    @NotNull
    @Positive
    private BigDecimal price;

    @NotBlank
    private String description;

    @NotBlank
    private String coverImage;
}
