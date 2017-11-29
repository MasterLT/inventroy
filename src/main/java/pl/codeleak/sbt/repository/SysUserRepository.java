package pl.codeleak.sbt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;
import pl.codeleak.sbt.entity.SysUser;

import java.util.List;

@Component
public interface SysUserRepository extends JpaRepository<SysUser, Integer> {

    List<SysUser> findByCodeAndPassword(String code,String password);

    List<SysUser> findByDepartmentKey(String departmentKey);

    @Query("SELECT u FROM SysUser u WHERE u.departmentKey=?1 and u.id in " +
            "(select r.sysuserId from SysuserRole r where r.RoleId=?2)")
    List<SysUser> getAdminByDepartmentKey(String departmentKey, Integer roleId);

}
