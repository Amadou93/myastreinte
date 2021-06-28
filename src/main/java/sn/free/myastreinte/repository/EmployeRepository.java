package sn.free.myastreinte.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import sn.free.myastreinte.domain.Employe;
import sn.free.myastreinte.service.dto.EmployeDTO;

import java.util.List;


/**
 * Spring Data  repository for the Employe entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EmployeRepository extends JpaRepository<Employe, Long> {
    @Query("Select e,d from Employe e,Equipe d where e.id=d.id")
    Page<EmployeDTO>findAllEmployeDTO(Pageable pageable);

    @Query("Select a from Employe a where a.equipe.name =:requete")
        List<EmployeDTO> findEmployeByRequete(@Param("requete") String requete);
}
