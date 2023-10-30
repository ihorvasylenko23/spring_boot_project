package mate.academy.dto.order;

import lombok.Data;
import mate.academy.model.enums.Status;

@Data
public class UpdateStatusRequestDto {
    private Status status;
}
