package sn.free.myastreinte.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.DayOfWeek;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.chrono.ChronoLocalDate;

/**
 * A Astreinte.
 */
@Entity
@Table(name = "astreinte")
public class Astreinte implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "matricul", nullable = false)
    private Long matricul;

    @Column(name = "year")
    private Long year;

    @NotNull
    @Column(name = "number_week", nullable = false)
    private Long numberWeek;

    @ManyToOne
    @JsonIgnoreProperties("astreintes")
    private Employe employe;
    /*private LocalDate week;
    private LocalDate start;*/

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMatricul() {
        return matricul;
    }

    public Astreinte matricul(Long matricul) {
        this.matricul = matricul;
        return this;
    }

    public void setMatricul(Long matricul) {
        this.matricul = matricul;
    }

    public Long getYear() {
        return year;
    }

    public Astreinte year(Long year) {
        this.year = year;
        return this;
    }

    public void setYear(Long year) {
        this.year = year;
    }

    public Long getNumberWeek() {
        return numberWeek;
    }

    public Astreinte numberWeek(Long numberWeek) {
        this.numberWeek = numberWeek;
        return this;
    }

    public void setNumberWeek(Long numberWeek) {
        this.numberWeek = numberWeek;
    }

    public Employe getEmploye() {
        return employe;
    }

    public Astreinte employe(Employe employe) {
        this.employe = employe;
        return this;
    }

    public void setEmploye(Employe employe) {
        this.employe = employe;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Astreinte)) {
            return false;
        }
        return id != null && id.equals(((Astreinte) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Astreinte{" +
            "id=" + getId() +
            ", matricul=" + getMatricul() +
            ", year=" + getYear() +
            ", numberWeek=" + getNumberWeek() +
            "}";
    }
}
