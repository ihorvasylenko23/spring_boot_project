package mate.academy.repositiry.role;

import mate.academy.model.Role;
import mate.academy.model.enums.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    @Query("SELECT r FROM Role r LEFT JOIN FETCH r.users WHERE r.name = :roleName")
    Role findByName(@Param("roleName") RoleName name);
}
