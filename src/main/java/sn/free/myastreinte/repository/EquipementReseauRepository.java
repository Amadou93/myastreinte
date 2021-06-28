package sn.free.myastreinte.repository;

import sn.free.myastreinte.domain.EquipementReseau;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the EquipementReseau entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EquipementReseauRepository extends JpaRepository<EquipementReseau, Long> {

}
