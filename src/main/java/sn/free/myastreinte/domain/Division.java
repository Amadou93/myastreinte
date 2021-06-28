package sn.free.myastreinte.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A Division.
 */
@Entity
@Table(name = "division")
public class Division implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(min = 3)
    @Column(name = "division_name", nullable = false)
    private String divisionName;

    @NotNull
    @Column(name = "division_chef", nullable = false)
    private String divisionChef;

    @ManyToOne
    @JsonIgnoreProperties("divisions")
    private Departement departement;

    @OneToMany(mappedBy = "division")
    private Set<Equipe> nameEquipes = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDivisionName() {
        return divisionName;
    }

    public Division divisionName(String divisionName) {
        this.divisionName = divisionName;
        return this;
    }

    public void setDivisionName(String divisionName) {
        this.divisionName = divisionName;
    }

    public String getDivisionChef() {
        return divisionChef;
    }

    public Division divisionChef(String divisionChef) {
        this.divisionChef = divisionChef;
        return this;
    }

    public void setDivisionChef(String divisionChef) {
        this.divisionChef = divisionChef;
    }

    public Departement getDepartement() {
        return departement;
    }

    public Division departement(Departement departement) {
        this.departement = departement;
        return this;
    }

    public void setDepartement(Departement departement) {
        this.departement = departement;
    }

    public Set<Equipe> getNameEquipes() {
        return nameEquipes;
    }

    public Division nameEquipes(Set<Equipe> equipes) {
        this.nameEquipes = equipes;
        return this;
    }

    public Division addNameEquipe(Equipe equipe) {
        this.nameEquipes.add(equipe);
        equipe.setDivision(this);
        return this;
    }

    public Division removeNameEquipe(Equipe equipe) {
        this.nameEquipes.remove(equipe);
        equipe.setDivision(null);
        return this;
    }

    public void setNameEquipes(Set<Equipe> equipes) {
        this.nameEquipes = equipes;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Division)) {
            return false;
        }
        return id != null && id.equals(((Division) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Division{" +
            "id=" + getId() +
            ", divisionName='" + getDivisionName() + "'" +
            ", divisionChef='" + getDivisionChef() + "'" +
            "}";
    }
}
