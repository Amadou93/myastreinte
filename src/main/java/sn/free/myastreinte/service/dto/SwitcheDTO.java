package sn.free.myastreinte.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link sn.free.myastreinte.domain.Switche} entity.
 */
public class SwitcheDTO implements Serializable {

    private Long id;

    @NotNull
    private Long ipAddress;


    private Long equipeId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(Long ipAddress) {
        this.ipAddress = ipAddress;
    }

    public Long getEquipeId() {
        return equipeId;
    }

    public void setEquipeId(Long equipeId) {
        this.equipeId = equipeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        SwitcheDTO switcheDTO = (SwitcheDTO) o;
        if (switcheDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), switcheDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "SwitcheDTO{" +
            "id=" + getId() +
            ", ipAddress=" + getIpAddress() +
            ", equipe=" + getEquipeId() +
            "}";
    }
}
