package sn.free.myastreinte.service.mapper;

import sn.free.myastreinte.domain.*;
import sn.free.myastreinte.service.dto.AbsenceDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Absence} and its DTO {@link AbsenceDTO}.
 */
@Mapper(componentModel = "spring", uses = {EmployeMapper.class})
public interface AbsenceMapper extends EntityMapper<AbsenceDTO, Absence> {

    @Mapping(source = "employe.id", target = "employeId")
    AbsenceDTO toDto(Absence absence);

    @Mapping(source = "employeId", target = "employe")
    Absence toEntity(AbsenceDTO absenceDTO);

    default Absence fromId(Long id) {
        if (id == null) {
            return null;
        }
        Absence absence = new Absence();
        absence.setId(id);
        return absence;
    }
}
