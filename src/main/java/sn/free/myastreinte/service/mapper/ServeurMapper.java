package sn.free.myastreinte.service.mapper;

import sn.free.myastreinte.domain.*;
import sn.free.myastreinte.service.dto.ServeurDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Serveur} and its DTO {@link ServeurDTO}.
 */
@Mapper(componentModel = "spring", uses = {EquipeMapper.class})
public interface ServeurMapper extends EntityMapper<ServeurDTO, Serveur> {

    @Mapping(source = "equipe.id", target = "equipeId")
    ServeurDTO toDto(Serveur serveur);

    @Mapping(source = "equipeId", target = "equipe")
    Serveur toEntity(ServeurDTO serveurDTO);

    default Serveur fromId(Long id) {
        if (id == null) {
            return null;
        }
        Serveur serveur = new Serveur();
        serveur.setId(id);
        return serveur;
    }
}
