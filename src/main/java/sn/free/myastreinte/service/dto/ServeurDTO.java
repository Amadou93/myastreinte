package sn.free.myastreinte.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link sn.free.myastreinte.domain.Serveur} entity.
 */
public class ServeurDTO implements Serializable {

    private Long id;

    @NotNull
    @Size(min = 3)
    private String nameServeur;

    @Size(min = 15)
    private String cpu;

    @NotNull
    private Long memory;

    @NotNull
    private Long disque;

    @NotNull
    private String avalibity;


    private Long equipeId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNameServeur() {
        return nameServeur;
    }

    public void setNameServeur(String nameServeur) {
        this.nameServeur = nameServeur;
    }

    public String getCpu() {
        return cpu;
    }

    public void setCpu(String cpu) {
        this.cpu = cpu;
    }

    public Long getMemory() {
        return memory;
    }

    public void setMemory(Long memory) {
        this.memory = memory;
    }

    public Long getDisque() {
        return disque;
    }

    public void setDisque(Long disque) {
        this.disque = disque;
    }

    public String getAvalibity() {
        return avalibity;
    }

    public void setAvalibity(String avalibity) {
        this.avalibity = avalibity;
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

        ServeurDTO serveurDTO = (ServeurDTO) o;
        if (serveurDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), serveurDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ServeurDTO{" +
            "id=" + getId() +
            ", nameServeur='" + getNameServeur() + "'" +
            ", cpu='" + getCpu() + "'" +
            ", memory=" + getMemory() +
            ", disque=" + getDisque() +
            ", avalibity='" + getAvalibity() + "'" +
            ", equipe=" + getEquipeId() +
            "}";
    }
}
