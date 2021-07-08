package sn.free.myastreinte.service.dto;
import java.time.Instant;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import sn.free.myastreinte.domain.enumeration.State;

/**
 * A DTO for the {@link sn.free.myastreinte.domain.Incident} entity.
 */
public class IncidentDTO implements Serializable {

    private Long id;

    @NotNull
    private Instant date;

    @NotNull
    @Size(min = 3)
    private String type;

    @Size(min = 3)
    private String criticite;

    private Long sla;

    @Size(min = 3)
    private String description;

    private String adresseIP;

    private String composant;

    private String responsable;

    private State status;

    private String equipementName;

    private String message;


    private Long equipeId;

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

    public String getCriticite() {
        return criticite;
    }

    public void setCriticite(String criticite) {
        this.criticite = criticite;
    }

    public Long getSla() {
        return sla;
    }

    public void setSla(Long sla) {
        this.sla = sla;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAdresseIP() {
        return adresseIP;
    }

    public void setAdresseIP(String adresseIP) {
        this.adresseIP = adresseIP;
    }

    public String getComposant() {
        return composant;
    }

    public void setComposant(String composant) {
        this.composant = composant;
    }

    public String getResponsable() {
        return responsable;
    }

    public void setResponsable(String responsable) {
        this.responsable = responsable;
    }

    public State getStatus() {
        return status;
    }

    public void setStatus(State status) {
        this.status = status;
    }

    public String getEquipementName() {
        return equipementName;
    }

    public void setEquipementName(String equipementName) {
        this.equipementName = equipementName;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
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

        IncidentDTO incidentDTO = (IncidentDTO) o;
        if (incidentDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), incidentDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "IncidentDTO{" +
            "id=" + getId() +
            ", date='" + getDate() + "'" +
            ", type='" + getType() + "'" +
            ", criticite='" + getCriticite() + "'" +
            ", sla=" + getSla() +
            ", description='" + getDescription() + "'" +
            ", adresseIP='" + getAdresseIP() + "'" +
            ", composant='" + getComposant() + "'" +
            ", responsable='" + getResponsable() + "'" +
            ", status='" + getStatus() + "'" +
            ", equipementName='" + getEquipementName() + "'" +
            ", message='" + getMessage() + "'" +
            ", equipe=" + getEquipeId() +
            "}";
    }
}
