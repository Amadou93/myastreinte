package sn.free.myastreinte.repository;

import sn.free.myastreinte.domain.Division;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Division entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DivisionRepository extends JpaRepository<Division, Long> {

}
