package sn.free.myastreinte.service.mapper;

import sn.free.myastreinte.domain.*;
import sn.free.myastreinte.service.dto.AstreinteDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Astreinte} and its DTO {@link AstreinteDTO}.
 */
@Mapper(componentModel = "spring", uses = {EmployeMapper.class})
public interface AstreinteMapper extends EntityMapper<AstreinteDTO, Astreinte> {

    @Mapping(source = "employe.id", target = "employeId")
    AstreinteDTO toDto(Astreinte astreinte);

    @Mapping(source = "employeId", target = "employe")
    Astreinte toEntity(AstreinteDTO astreinteDTO);

    default Astreinte fromId(Long id) {
        if (id == null) {
            return null;
        }
        Astreinte astreinte = new Astreinte();
        astreinte.setId(id);
        return astreinte;
    }
}
