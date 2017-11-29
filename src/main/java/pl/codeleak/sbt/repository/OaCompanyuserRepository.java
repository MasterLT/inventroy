package pl.codeleak.sbt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import pl.codeleak.sbt.entity.OaCompanyuser;

import java.util.List;

@Component
public interface OaCompanyuserRepository extends JpaRepository<OaCompanyuser, String> {
    List<OaCompanyuser> findByLastnameAndSupname(String lastname, String supname);
}
