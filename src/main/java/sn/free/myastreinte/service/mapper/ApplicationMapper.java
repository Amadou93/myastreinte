package sn.free.myastreinte.service.mapper;

import sn.free.myastreinte.domain.*;
import sn.free.myastreinte.service.dto.ApplicationDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Application} and its DTO {@link ApplicationDTO}.
 */
@Mapper(componentModel = "spring", uses = {EquipeMapper.class})
public interface ApplicationMapper extends EntityMapper<ApplicationDTO, Application> {

    @Mapping(source = "equipe.id", target = "equipeId")
    ApplicationDTO toDto(Application application);

    @Mapping(source = "equipeId", target = "equipe")
    Application toEntity(ApplicationDTO applicationDTO);

    default Application fromId(Long id) {
        if (id == null) {
            return null;
        }
        Application application = new Application();
        application.setId(id);
        return application;
    }
}
