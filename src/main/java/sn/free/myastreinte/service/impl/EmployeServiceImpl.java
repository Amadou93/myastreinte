package sn.free.myastreinte.service.impl;

import org.json.JSONObject;
/*import org.springframework.boot.configurationprocessor.json.JSONObject;*/
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;
import sn.free.myastreinte.service.EmployeService;
import sn.free.myastreinte.domain.Employe;
import sn.free.myastreinte.repository.EmployeRepository;
import sn.free.myastreinte.service.dto.EmployeDTO;
import sn.free.myastreinte.service.mapper.EmployeMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;
import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing {@link Employe}.
 */
@Service
@Transactional
public class EmployeServiceImpl implements EmployeService {

    private final Logger log = LoggerFactory.getLogger(EmployeServiceImpl.class);

    private final EmployeRepository employeRepository;

    private final EmployeMapper employeMapper;
    private Pageable Pageable;

    public EmployeServiceImpl(EmployeRepository employeRepository, EmployeMapper employeMapper) {
        this.employeRepository = employeRepository;
        this.employeMapper = employeMapper;
    }


    /**
     * Save a employe.
     *
     * @param employeDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public EmployeDTO save(EmployeDTO employeDTO) {
        log.debug("Request to save Employe : {}", employeDTO);
        Employe employe = employeMapper.toEntity(employeDTO);
        employe = employeRepository.save(employe);
        return employeMapper.toDto(employe);
    }

    /**
     * Get all the employes.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<EmployeDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Employes");
        return employeRepository.findAll(pageable)
            .map(employeMapper::toDto);
    }


    /**
     * Get one employe by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<EmployeDTO> findOne(Long id) {
        log.debug("Request to get Employe : {}", id);
        return employeRepository.findById(id)
            .map(employeMapper::toDto);
    }

    /**
     * Delete the employe by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Employe : {}", id);
        employeRepository.deleteById(id);
    }

    @Override
    public Page<EmployeDTO> findAllEmployeDTO(Pageable pageable) {
        return employeRepository.findAll(pageable)
            .map(employeMapper::toDto);
    }



    @Override
    public List<EmployeDTO> findEmployeDTOByRequete(String requete) {
        return employeRepository.findEmployeByRequete( requete);
    }


    /**
     * Get all the employes.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<EmployeDTO> findAllEmploye(Pageable pageable) {
        log.debug("Request to get all Employes");

         return employeRepository.findAllEmployeDTO(pageable);
        /*(Page<EmployeDTO>) EmployeRepository.findALLEmployeDTO(pageable);*/
    }


    @Override
   public boolean sendSms(String messageTosend, String sender, String receiverMsisdn, int type){
        //ignoring ssl certificatee
        HttpsURLConnection.setDefaultHostnameVerifier(new HostnameVerifier() {
            public boolean verify(String hostname, SSLSession session) {
                return true;
            }
        });
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON) ;
        JSONObject sendSmsRequest = new JSONObject();
        try {
            sendSmsRequest.put("messageToSend","Bonjour");
            sendSmsRequest.put("receiverMSISDN","221766753014");
            sendSmsRequest.put("sender","Astreinte");
            sendSmsRequest.put("type",0);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        System.out.println("REQUEST BODY :" + sendSmsRequest.toString());
        HttpEntity<String> request = new HttpEntity<String>(sendSmsRequest.toString(), headers);
        String sendSmsResultStr = "";
        try{
            sendSmsResultStr = restTemplate.postForObject( "https://192.168.41.45:8033/services/SendNotifications.aspx?_wadl", request, String.class);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        System.out.println("RESPONSE BODY :" + sendSmsResultStr);
        return sendSmsResultStr.equalsIgnoreCase("0");
    }


}
