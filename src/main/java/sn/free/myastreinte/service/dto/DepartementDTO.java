package sn.free.myastreinte.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link sn.free.myastreinte.domain.Departement} entity.
 */
public class DepartementDTO implements Serializable {

    private Long id;

    @NotNull
    private String departementName;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDepartementName() {
        return departementName;
    }

    public void setDepartementName(String departementName) {
        this.departementName = departementName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        DepartementDTO departementDTO = (DepartementDTO) o;
        if (departementDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), departementDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "DepartementDTO{" +
            "id=" + getId() +
            ", departementName='" + getDepartementName() + "'" +
            "}";
    }
}
