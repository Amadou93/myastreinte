package sn.free.myastreinte.service.mapper;

import sn.free.myastreinte.domain.*;
import sn.free.myastreinte.service.dto.IncidentDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Incident} and its DTO {@link IncidentDTO}.
 */
@Mapper(componentModel = "spring", uses = {EquipeMapper.class})
public interface IncidentMapper extends EntityMapper<IncidentDTO, Incident> {

    @Mapping(source = "equipe.id", target = "equipeId")
    IncidentDTO toDto(Incident incident);

    @Mapping(source = "equipeId", target = "equipe")
    Incident toEntity(IncidentDTO incidentDTO);

    default Incident fromId(Long id) {
        if (id == null) {
            return null;
        }
        Incident incident = new Incident();
        incident.setId(id);
        return incident;
    }
}
