package sn.free.myastreinte.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;

/**
 * A Basededonnee.
 */
@Entity
@Table(name = "basededonnee")
public class Basededonnee implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(min = 2)
    @Column(name = "name", nullable = false)
    private String name;

    @NotNull
    @Size(min = 3)
    @Column(name = "base_type", nullable = false)
    private String baseType;

    @NotNull
    @Column(name = "memory", nullable = false)
    private String memory;

    @ManyToOne
    @JsonIgnoreProperties("basededonnees")
    private Equipe equipe;

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

    public Basededonnee name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBaseType() {
        return baseType;
    }

    public Basededonnee baseType(String baseType) {
        this.baseType = baseType;
        return this;
    }

    public void setBaseType(String baseType) {
        this.baseType = baseType;
    }

    public String getMemory() {
        return memory;
    }

    public Basededonnee memory(String memory) {
        this.memory = memory;
        return this;
    }

    public void setMemory(String memory) {
        this.memory = memory;
    }

    public Equipe getEquipe() {
        return equipe;
    }

    public Basededonnee equipe(Equipe equipe) {
        this.equipe = equipe;
        return this;
    }

    public void setEquipe(Equipe equipe) {
        this.equipe = equipe;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Basededonnee)) {
            return false;
        }
        return id != null && id.equals(((Basededonnee) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Basededonnee{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", baseType='" + getBaseType() + "'" +
            ", memory='" + getMemory() + "'" +
            "}";
    }
}
