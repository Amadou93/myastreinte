package sn.free.myastreinte.service;

import org.springframework.stereotype.Service;
import sn.free.myastreinte.domain.Employe;
import sn.free.myastreinte.service.dto.EmployeDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link sn.free.myastreinte.domain.Employe}.
 */
@Service
public interface EmployeService {


    /**
     * Send sms.
     *
     * @param messageTosend .
     * @param sender .
     * @param receiverMsisdn .
     * @param type .
     * @return boolean.
     */
    boolean sendSms(String messageTosend, String sender, String receiverMsisdn, int type);



    /**
     * Save a employe.
     *
     * @param employeDTO the entity to save.
     * @return the persisted entity.
     */
    EmployeDTO save(EmployeDTO employeDTO);

    /**
     * Get all the employes.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<EmployeDTO> findAll(Pageable pageable);


    /**
     * Get the "id" employe.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<EmployeDTO> findOne(Long id);

    /**
     * Delete the "id" employe.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);

    Page<EmployeDTO> findAllEmployeDTO(Pageable pageable);

    /*Page<EmployeDTO> findAllEmploye(Pageable pageable);
*/
   /* Page<EmployeDTO> findAllEmployeDTO(Pageable pageable);*/



   /* Page<EmployeDTO> findAllEmployeDTO(Pageable pageable);
    List<EmployeDTO> findEmployeDTOByRequete(String requete);*/
}
