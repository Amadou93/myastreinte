package sn.free.myastreinte.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link sn.free.myastreinte.domain.EquipementReseau} entity.
 */
public class EquipementReseauDTO implements Serializable {

    private Long id;

    @Size(min = 3)
    private String equipementName;

    @Size(min = 3)
    private String type;

    private Long ipAddress;

    private Long version;


    private Long equipeId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEquipementName() {
        return equipementName;
    }

    public void setEquipementName(String equipementName) {
        this.equipementName = equipementName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(Long ipAddress) {
        this.ipAddress = ipAddress;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
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

        EquipementReseauDTO equipementReseauDTO = (EquipementReseauDTO) o;
        if (equipementReseauDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), equipementReseauDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "EquipementReseauDTO{" +
            "id=" + getId() +
            ", equipementName='" + getEquipementName() + "'" +
            ", type='" + getType() + "'" +
            ", ipAddress=" + getIpAddress() +
            ", version=" + getVersion() +
            ", equipe=" + getEquipeId() +
            "}";
    }
}
