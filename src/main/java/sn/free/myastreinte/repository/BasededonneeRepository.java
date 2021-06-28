package sn.free.myastreinte.repository;

import sn.free.myastreinte.domain.Basededonnee;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Basededonnee entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BasededonneeRepository extends JpaRepository<Basededonnee, Long> {

}
