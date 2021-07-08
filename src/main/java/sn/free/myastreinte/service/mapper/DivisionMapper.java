package sn.free.myastreinte.service.mapper;

import sn.free.myastreinte.domain.*;
import sn.free.myastreinte.service.dto.DivisionDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Division} and its DTO {@link DivisionDTO}.
 */
@Mapper(componentModel = "spring", uses = {DepartementMapper.class})
public interface DivisionMapper extends EntityMapper<DivisionDTO, Division> {

    @Mapping(source = "departement.id", target = "departementId")
    DivisionDTO toDto(Division division);

    @Mapping(source = "departementId", target = "departement")
    @Mapping(target = "equipes", ignore = true)
    @Mapping(target = "removeEquipe", ignore = true)
    Division toEntity(DivisionDTO divisionDTO);

    default Division fromId(Long id) {
        if (id == null) {
            return null;
        }
        Division division = new Division();
        division.setId(id);
        return division;
    }
}
