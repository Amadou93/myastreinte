package sn.free.myastreinte.service.mapper;

import sn.free.myastreinte.domain.*;
import sn.free.myastreinte.service.dto.EquipementReseauDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link EquipementReseau} and its DTO {@link EquipementReseauDTO}.
 */
@Mapper(componentModel = "spring", uses = {EquipeMapper.class})
public interface EquipementReseauMapper extends EntityMapper<EquipementReseauDTO, EquipementReseau> {

    @Mapping(source = "equipe.id", target = "equipeId")
    EquipementReseauDTO toDto(EquipementReseau equipementReseau);

    @Mapping(source = "equipeId", target = "equipe")
    EquipementReseau toEntity(EquipementReseauDTO equipementReseauDTO);

    default EquipementReseau fromId(Long id) {
        if (id == null) {
            return null;
        }
        EquipementReseau equipementReseau = new EquipementReseau();
        equipementReseau.setId(id);
        return equipementReseau;
    }
}
