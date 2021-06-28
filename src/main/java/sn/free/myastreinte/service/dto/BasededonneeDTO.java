package sn.free.myastreinte.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link sn.free.myastreinte.domain.Basededonnee} entity.
 */
public class BasededonneeDTO implements Serializable {

    private Long id;

    @NotNull
    @Size(min = 2)
    private String name;

    @NotNull
    @Size(min = 3)
    private String baseType;

    @NotNull
    private String memory;


    private Long equipeId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBaseType() {
        return baseType;
    }

    public void setBaseType(String baseType) {
        this.baseType = baseType;
    }

    public String getMemory() {
        return memory;
    }

    public void setMemory(String memory) {
        this.memory = memory;
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

        BasededonneeDTO basededonneeDTO = (BasededonneeDTO) o;
        if (basededonneeDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), basededonneeDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "BasededonneeDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", baseType='" + getBaseType() + "'" +
            ", memory='" + getMemory() + "'" +
            ", equipe=" + getEquipeId() +
            "}";
    }
}
