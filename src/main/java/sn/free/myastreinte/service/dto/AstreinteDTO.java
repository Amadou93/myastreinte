package sn.free.myastreinte.service.dto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link sn.free.myastreinte.domain.Astreinte} entity.
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AstreinteDTO implements Serializable {

    private Long id;

    @NotNull
    private Long matricul;

    private Long year;

    @NotNull
    private Long numberWeek;


    private Long employeId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMatricul() {
        return matricul;
    }

    public void setMatricul(Long matricul) {
        this.matricul = matricul;
    }

    public Long getYear() {
        return year;
    }

    public void setYear(Long year) {
        this.year = year;
    }

    public Long getNumberWeek() {
        return numberWeek;
    }

    public void setNumberWeek(Long numberWeek) {
        this.numberWeek = numberWeek;
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

        AstreinteDTO astreinteDTO = (AstreinteDTO) o;
        if (astreinteDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), astreinteDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "AstreinteDTO{" +
            "id=" + getId() +
            ", matricul=" + getMatricul() +
            ", year=" + getYear() +
            ", numberWeek=" + getNumberWeek() +
            ", employe=" + getEmployeId() +

            "}";
    }
}
