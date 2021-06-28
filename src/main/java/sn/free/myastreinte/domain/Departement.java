package sn.free.myastreinte.domain;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A Departement.
 */
@Entity
@Table(name = "departement")
public class Departement implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "departement_name", nullable = false)
    private String departementName;

    @OneToMany(mappedBy = "departement")
    private Set<Division> divisions = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDepartementName() {
        return departementName;
    }

    public Departement departementName(String departementName) {
        this.departementName = departementName;
        return this;
    }

    public void setDepartementName(String departementName) {
        this.departementName = departementName;
    }

    public Set<Division> getDivisions() {
        return divisions;
    }

    public Departement divisions(Set<Division> divisions) {
        this.divisions = divisions;
        return this;
    }

    public Departement addDivision(Division division) {
        this.divisions.add(division);
        division.setDepartement(this);
        return this;
    }

    public Departement removeDivision(Division division) {
        this.divisions.remove(division);
        division.setDepartement(null);
        return this;
    }

    public void setDivisions(Set<Division> divisions) {
        this.divisions = divisions;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Departement)) {
            return false;
        }
        return id != null && id.equals(((Departement) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Departement{" +
            "id=" + getId() +
            ", departementName='" + getDepartementName() + "'" +
            "}";
    }
}
