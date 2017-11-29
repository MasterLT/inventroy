package pl.codeleak.sbt.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;
import pl.codeleak.sbt.entity.Inventory;
import pl.codeleak.sbt.entity.InventoryHistory;

import java.util.Date;
import java.util.List;

@Component
public interface InventoryHistoryRepository extends JpaRepository<InventoryHistory, Integer> {

    Page<InventoryHistory> findByInventoryIdAndAssetcodeLike(Integer inventoryId, String assetcode, Pageable pageable);

    List<InventoryHistory> findByInventoryIdAndAssetcodeLike(Integer inventoryId, String assetcode);

    Page<InventoryHistory> findByInventoryIdAndAssetcodeLikeAndInventorystate(Integer inventoryId, String assetcode, Integer inventorystate, Pageable pageable);

    Page<InventoryHistory> findByInventoryIdAndDeptnameAndAssetcodeLike(Integer inventoryId, String deptname, String assetcode, Pageable pageable);

    Page<InventoryHistory> findByInventoryIdAndDeptnameAndAssetcodeLikeAndInventorystate(Integer inventoryId, String deptname, String assetcode, Integer inventorystate, Pageable pageable);

    @Query("select count(a.id) from InventoryHistory a where a.inventoryId=?1 and a.inventorystate=?2")
    Long countByInventoryIdAndState(Integer inventoryId, Integer state);
}
