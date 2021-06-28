package sn.free.myastreinte.service.mapper;

import sn.free.myastreinte.domain.*;
import sn.free.myastreinte.service.dto.EmployeDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Employe} and its DTO {@link EmployeDTO}.
 */
@Mapper(componentModel = "spring", uses = {EquipeMapper.class})
public interface EmployeMapper extends EntityMapper<EmployeDTO, Employe> {

    @Mapping(source = "equipe.id", target = "equipeId")
    EmployeDTO toDto(Employe employe);

    @Mapping(target = "astreintes", ignore = true)
    @Mapping(target = "removeAstreinte", ignore = true)
    @Mapping(target = "notifications", ignore = true)
    @Mapping(target = "removeNotification", ignore = true)
    @Mapping(target = "absences", ignore = true)
    @Mapping(target = "removeAbsence", ignore = true)
    @Mapping(source = "equipeId", target = "equipe")
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
