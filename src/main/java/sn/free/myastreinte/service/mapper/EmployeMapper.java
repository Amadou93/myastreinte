package sn.free.myastreinte.service.mapper;

import sn.free.myastreinte.domain.*;
import sn.free.myastreinte.service.dto.EmployeDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Employe} and its DTO {@link EmployeDTO}.
 */
@Mapper(componentModel = "spring", uses = {EquipeMapper.class, DomaineMapper.class})
public interface EmployeMapper extends EntityMapper<EmployeDTO, Employe> {

    @Mapping(source = "equipe.id", target = "equipeId")
   /* @Mapping(source = "domaine.id", target = "employe.domaine.id")*/
    EmployeDTO toDto(Employe employe);

    @Mapping(source = "equipeId", target = "equipe")
  /*  @Mapping(source = "domaine.id", target = "domaine")*/
    Employe toEntity(EmployeDTO employeDTO);

    default Employe fromId(Long id) {
        if (id == null) {
            return null;
        }
        Employe employe = new Employe();
        employe.setId(id);
        return employe;
    }
}
