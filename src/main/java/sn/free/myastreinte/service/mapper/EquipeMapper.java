package sn.free.myastreinte.service.mapper;

import sn.free.myastreinte.domain.*;
import sn.free.myastreinte.service.dto.EquipeDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Equipe} and its DTO {@link EquipeDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface EquipeMapper extends EntityMapper<EquipeDTO, Equipe> {


    @Mapping(target = "employes", ignore = true)
    @Mapping(target = "removeEmploye", ignore = true)
    Equipe toEntity(EquipeDTO equipeDTO);

    default Equipe fromId(Long id) {
        if (id == null) {
            return null;
        }
        Equipe equipe = new Equipe();
        equipe.setId(id);
        return equipe;
    }
}
