package mate.academy.dto.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import mate.academy.validation.FieldMatch;

@Data
@FieldMatch(
        first = "password",
        second = "repeatPassword",
        message = "Passwords must match"
)
public class UserRegistrationRequestDto {
    @NotBlank
    @Size(min = 8, max = 20)
    private String email;
    @NotBlank
    @Size(min = 8, max = 20)
    private String password;
    @NotBlank
    @Size(min = 8, max = 20)
    private String repeatPassword;
    @NotBlank
    @Size(min = 1, max = 100)
    private String firstName;
    @NotBlank
    @Size(min = 1, max = 100)
    private String lastName;
    @NotBlank
    private String shippingAddress;
}
