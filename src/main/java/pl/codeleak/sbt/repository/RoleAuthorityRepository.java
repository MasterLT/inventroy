package pl.codeleak.sbt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import pl.codeleak.sbt.entity.RoleAuthority;
import pl.codeleak.sbt.entity.SysuserRole;

import java.util.List;

@Component
public interface RoleAuthorityRepository extends JpaRepository<RoleAuthority, Integer> {

    List<RoleAuthority> findByRoleIdIn(List<Integer> roleIds);

}
