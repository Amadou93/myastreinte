package sn.free.myastreinte.service.mapper;

import sn.free.myastreinte.domain.*;
import sn.free.myastreinte.service.dto.BasededonneeDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Basededonnee} and its DTO {@link BasededonneeDTO}.
 */
@Mapper(componentModel = "spring", uses = {EquipeMapper.class})
public interface BasededonneeMapper extends EntityMapper<BasededonneeDTO, Basededonnee> {

    @Mapping(source = "equipe.id", target = "equipeId")
    BasededonneeDTO toDto(Basededonnee basededonnee);

    @Mapping(source = "equipeId", target = "equipe")
    Basededonnee toEntity(BasededonneeDTO basededonneeDTO);

    default Basededonnee fromId(Long id) {
        if (id == null) {
            return null;
        }
        Basededonnee basededonnee = new Basededonnee();
        basededonnee.setId(id);
        return basededonnee;
    }
}
