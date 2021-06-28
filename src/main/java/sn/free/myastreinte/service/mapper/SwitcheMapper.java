package sn.free.myastreinte.service.mapper;

import sn.free.myastreinte.domain.*;
import sn.free.myastreinte.service.dto.SwitcheDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Switche} and its DTO {@link SwitcheDTO}.
 */
@Mapper(componentModel = "spring", uses = {EquipeMapper.class})
public interface SwitcheMapper extends EntityMapper<SwitcheDTO, Switche> {

    @Mapping(source = "equipe.id", target = "equipeId")
    SwitcheDTO toDto(Switche switche);

    @Mapping(source = "equipeId", target = "equipe")
    Switche toEntity(SwitcheDTO switcheDTO);

    default Switche fromId(Long id) {
        if (id == null) {
            return null;
        }
        Switche switche = new Switche();
        switche.setId(id);
        return switche;
    }
}
