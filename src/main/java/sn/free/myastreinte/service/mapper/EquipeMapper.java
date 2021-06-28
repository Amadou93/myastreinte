package sn.free.myastreinte.service.mapper;

import sn.free.myastreinte.domain.*;
import sn.free.myastreinte.service.dto.EquipeDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Equipe} and its DTO {@link EquipeDTO}.
 */
@Mapper(componentModel = "spring", uses = {DivisionMapper.class})
public interface EquipeMapper extends EntityMapper<EquipeDTO, Equipe> {

    @Mapping(source = "division.id", target = "divisionId")
    EquipeDTO toDto(Equipe equipe);

    @Mapping(target = "employes", ignore = true)
    @Mapping(target = "removeEmploye", ignore = true)
    @Mapping(target = "serveurs", ignore = true)
    @Mapping(target = "removeServeur", ignore = true)
    @Mapping(target = "equipements", ignore = true)
    @Mapping(target = "removeEquipement", ignore = true)
    @Mapping(target = "switches", ignore = true)
    @Mapping(target = "removeSwitche", ignore = true)
    @Mapping(target = "dates", ignore = true)
    @Mapping(target = "removeDate", ignore = true)
    @Mapping(target = "applications", ignore = true)
    @Mapping(target = "removeApplication", ignore = true)
    @Mapping(target = "basededonnees", ignore = true)
    @Mapping(target = "removeBasededonnee", ignore = true)
    @Mapping(source = "divisionId", target = "division")
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
