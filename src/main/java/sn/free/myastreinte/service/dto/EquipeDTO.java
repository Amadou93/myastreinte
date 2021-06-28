package sn.free.myastreinte.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link sn.free.myastreinte.domain.Equipe} entity.
 */
public class EquipeDTO implements Serializable {

    private Long id;

    @NotNull
    @Size(min = 3)
    private String name;


    private Long divisionId;

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

    public Long getDivisionId() {
        return divisionId;
    }

    public void setDivisionId(Long divisionId) {
        this.divisionId = divisionId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        EquipeDTO equipeDTO = (EquipeDTO) o;
        if (equipeDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), equipeDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "EquipeDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", division=" + getDivisionId() +
            "}";
    }
}
