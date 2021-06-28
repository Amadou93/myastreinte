package sn.free.myastreinte.service.mapper;

import sn.free.myastreinte.domain.*;
import sn.free.myastreinte.service.dto.NotificationDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Notification} and its DTO {@link NotificationDTO}.
 */
@Mapper(componentModel = "spring", uses = {EmployeMapper.class})
public interface NotificationMapper extends EntityMapper<NotificationDTO, Notification> {

    @Mapping(source = "employe.id", target = "employeId")
    NotificationDTO toDto(Notification notification);

    @Mapping(source = "employeId", target = "employe")
    Notification toEntity(NotificationDTO notificationDTO);

    default Notification fromId(Long id) {
        if (id == null) {
            return null;
        }
        Notification notification = new Notification();
        notification.setId(id);
        return notification;
    }
}
