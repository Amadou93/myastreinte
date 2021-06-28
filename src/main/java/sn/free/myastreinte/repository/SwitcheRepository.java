package sn.free.myastreinte.repository;

import sn.free.myastreinte.domain.Switche;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Switche entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SwitcheRepository extends JpaRepository<Switche, Long> {

}
