package sn.free.myastreinte.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import sn.free.myastreinte.domain.Equipe;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Equipe entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EquipeRepository extends JpaRepository<Equipe, Long> {
    @Override
    Page<Equipe> findAll(Pageable pageable);
}
