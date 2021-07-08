package sn.free.myastreinte.domain;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A Equipe.
 */
@Entity
@Table(name = "equipe")
public class Equipe implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(min = 3)
    @Column(name = "name", nullable = false)
    private String name;

    @OneToMany(mappedBy = "equipe")
    private Set<Employe> employes = new HashSet<>();
    @ManyToOne
    private Division division;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Equipe name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Employe> getEmployes() {
        return employes;
    }

    public Equipe employes(Set<Employe> employes) {
        this.employes = employes;
        return this;
    }

    public Equipe addEmploye(Employe employe) {
        this.employes.add(employe);
        employe.setEquipe(this);
        return this;
    }

    public Equipe removeEmploye(Employe employe) {
        this.employes.remove(employe);
        employe.setEquipe(null);
        return this;
    }

    public void setEmployes(Set<Employe> employes) {
        this.employes = employes;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Equipe)) {
            return false;
        }
        return id != null && id.equals(((Equipe) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Equipe{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            "}";
    }

    public void setDivision(Division division) {
        this.division = division;
    }

    public Division getDivision() {
        Division division;
        Division divisions;
        return this.division;
    }
}
