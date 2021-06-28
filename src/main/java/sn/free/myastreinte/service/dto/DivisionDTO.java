package sn.free.myastreinte.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link sn.free.myastreinte.domain.Division} entity.
 */
public class DivisionDTO implements Serializable {

    private Long id;

    @NotNull
    @Size(min = 3)
    private String divisionName;

    @NotNull
    private String divisionChef;


    private Long departementId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDivisionName() {
        return divisionName;
    }

    public void setDivisionName(String divisionName) {
        this.divisionName = divisionName;
    }

    public String getDivisionChef() {
        return divisionChef;
    }

    public void setDivisionChef(String divisionChef) {
        this.divisionChef = divisionChef;
    }

    public Long getDepartementId() {
        return departementId;
    }

    public void setDepartementId(Long departementId) {
        this.departementId = departementId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        DivisionDTO divisionDTO = (DivisionDTO) o;
        if (divisionDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), divisionDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "DivisionDTO{" +
            "id=" + getId() +
            ", divisionName='" + getDivisionName() + "'" +
            ", divisionChef='" + getDivisionChef() + "'" +
            ", departement=" + getDepartementId() +
            "}";
    }
}
