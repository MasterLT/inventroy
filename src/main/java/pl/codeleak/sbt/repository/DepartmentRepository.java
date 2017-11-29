package pl.codeleak.sbt.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import pl.codeleak.sbt.entity.Authority;
import pl.codeleak.sbt.entity.Department;

import java.util.Collection;
import java.util.List;

@Component
public interface DepartmentRepository extends JpaRepository<Department, Integer> {
    List<Department> findByParentDepartmentkey(String parentDepartmentkey);

    Page<Department> findByParentDepartmentkey(String parentDepartmentkey, Pageable pageable);

    Page<Department> findById(Integer id, Pageable pageable);

    List<Department> findByDepartmentKey(String departmentKey);
}
