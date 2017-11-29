package pl.codeleak.sbt.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;
import pl.codeleak.sbt.entity.Asset;
import pl.codeleak.sbt.entity.Department;
import pl.codeleak.sbt.entity.Inventory;

import java.util.Date;

@Component
public interface InventoryRepository extends JpaRepository<Inventory, Integer> {

    @Query("SELECT max (i.startTime) FROM Inventory i WHERE i.departmentId=?1")
    Date getlastInventoryTimeByDeptId(Integer departmentId);

    Page<Inventory> findByDepartmentIdAndState(Integer departmentId, Integer state, Pageable pageable);

    Page<Inventory> findByState(Integer state, Pageable pageable);

    Page<Inventory> findByDepartmentId(Integer departmentId, Pageable pageable);

}
