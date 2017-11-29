package pl.codeleak.sbt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import pl.codeleak.sbt.entity.SysUser;
import pl.codeleak.sbt.entity.SysuserRole;

import java.util.List;

@Component
public interface SysuserRoleRepository extends JpaRepository<SysuserRole, Integer> {

    List<SysuserRole> findBySysuserId(Integer sysuserId);

}
