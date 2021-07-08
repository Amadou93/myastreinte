package sn.free.myastreinte.service.mapper;

import sn.free.myastreinte.domain.*;
import sn.free.myastreinte.service.dto.DomaineDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Domaine} and its DTO {@link DomaineDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface DomaineMapper extends EntityMapper<DomaineDTO, Domaine> {



    default Domaine fromId(Long id) {
        if (id == null) {
            return null;
        }
        Domaine domaine = new Domaine();
        domaine.setId(id);
        return domaine;
    }
}
