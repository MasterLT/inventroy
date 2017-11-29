package pl.codeleak.sbt.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;
import pl.codeleak.sbt.entity.Asset;
import pl.codeleak.sbt.entity.Department;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.Date;
import java.util.List;

@Component
public interface AssetRepository extends JpaRepository<Asset, String> {

    @Query("SELECT count(a.assetcode) FROM Asset a WHERE a.deptname=?1 and a.state=?2")
    Long countAssetNumByDeptIdAndState(String deptname, Integer state);

    List<Asset> findByDeptnameAndState(String deptname, Integer state);

    List<Asset> findByAssetcode(String assetcode);

    Page<Asset> findByPlaceAndAssetcodeLikeAndDeptnameAndState(String place, String assetcode, String deptname, Integer state, Pageable pageable);

    Page<Asset> findByAssetcodeLikeAndDeptnameAndState(String assetcode, String deptname, Integer state, Pageable pageable);
}
