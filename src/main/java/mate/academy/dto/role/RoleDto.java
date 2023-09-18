package mate.academy.dto.role;

import lombok.Data;
import mate.academy.model.enums.RoleName;

@Data
public class RoleDto {
    private Long id;
    private RoleName name;
}
