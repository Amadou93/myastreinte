package sn.free.myastreinte.repository;

import sn.free.myastreinte.domain.Serveur;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Serveur entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ServeurRepository extends JpaRepository<Serveur, Long> {

}
