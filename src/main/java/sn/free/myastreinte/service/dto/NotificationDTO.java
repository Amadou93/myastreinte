package sn.free.myastreinte.service.dto;
import java.time.Instant;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import sn.free.myastreinte.domain.enumeration.State;

/**
 * A DTO for the {@link sn.free.myastreinte.domain.Notification} entity.
 */
public class NotificationDTO implements Serializable {

    private Long id;

    @NotNull
    private Instant date;

    @NotNull
    private String type;

    private Long level;

    @Size(min = 3)
    private String dispositifName;

    private State state;

    @Size(min = 3)
    private String groupe;

    @Size(min = 3)
    private String astreinteName;

    private Instant availiblity;

    private String contact;

    private String status;

    private String message;


    private Long employeId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instant getDate() {
        return date;
    }

    public void setDate(Instant date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getLevel() {
        return level;
    }

    public void setLevel(Long level) {
        this.level = level;
    }

    public String getDispositifName() {
        return dispositifName;
    }

    public void setDispositifName(String dispositifName) {
        this.dispositifName = dispositifName;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public String getGroupe() {
        return groupe;
    }

    public void setGroupe(String groupe) {
        this.groupe = groupe;
    }

    public String getAstreinteName() {
        return astreinteName;
    }

    public void setAstreinteName(String astreinteName) {
        this.astreinteName = astreinteName;
    }

    public Instant getAvailiblity() {
        return availiblity;
    }

    public void setAvailiblity(Instant availiblity) {
        this.availiblity = availiblity;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Long getEmployeId() {
        return employeId;
    }

    public void setEmployeId(Long employeId) {
        this.employeId = employeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        NotificationDTO notificationDTO = (NotificationDTO) o;
        if (notificationDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), notificationDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "NotificationDTO{" +
            "id=" + getId() +
            ", date='" + getDate() + "'" +
            ", type='" + getType() + "'" +
            ", level=" + getLevel() +
            ", dispositifName='" + getDispositifName() + "'" +
            ", state='" + getState() + "'" +
            ", groupe='" + getGroupe() + "'" +
            ", astreinteName='" + getAstreinteName() + "'" +
            ", availiblity='" + getAvailiblity() + "'" +
            ", contact='" + getContact() + "'" +
            ", status='" + getStatus() + "'" +
            ", message='" + getMessage() + "'" +
            ", employe=" + getEmployeId() +
            "}";
    }
}
