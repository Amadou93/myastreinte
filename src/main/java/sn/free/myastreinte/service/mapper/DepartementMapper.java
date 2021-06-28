package sn.free.myastreinte.service.mapper;

import sn.free.myastreinte.domain.*;
import sn.free.myastreinte.service.dto.DepartementDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Departement} and its DTO {@link DepartementDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface DepartementMapper extends EntityMapper<DepartementDTO, Departement> {


    @Mapping(target = "divisions", ignore = true)
    @Mapping(target = "removeDivision", ignore = true)
    Departement toEntity(DepartementDTO departementDTO);

    default Departement fromId(Long id) {
        if (id == null) {
            return null;
        }
        Departement departement = new Departement();
        departement.setId(id);
        return departement;
    }
}
