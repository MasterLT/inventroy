package pl.codeleak.sbt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import pl.codeleak.sbt.entity.Authority;
import pl.codeleak.sbt.entity.RoleAuthority;

import java.util.List;

@Component
public interface AuthorityRepository extends JpaRepository<Authority, Integer> {

    List<Authority> findByIdInAndParentMenuidOrderBySequenceDesc(List<Integer> ids,Integer parentMenuid);

    List<Authority> findByParentMenuidOrderBySequenceDesc(Integer parentMenuid);

}
