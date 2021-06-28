package sn.free.myastreinte.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;

/**
 * A Serveur.
 */
@Entity
@Table(name = "serveur")
public class Serveur implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(min = 3)
    @Column(name = "name_serveur", nullable = false)
    private String nameServeur;

    @Size(min = 15)
    @Column(name = "cpu")
    private String cpu;

    @NotNull
    @Column(name = "memory", nullable = false)
    private Long memory;

    @NotNull
    @Column(name = "disque", nullable = false)
    private Long disque;

    @NotNull
    @Column(name = "avalibity", nullable = false)
    private String avalibity;

    @ManyToOne
    @JsonIgnoreProperties("serveurs")
    private Equipe equipe;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNameServeur() {
        return nameServeur;
    }

    public Serveur nameServeur(String nameServeur) {
        this.nameServeur = nameServeur;
        return this;
    }

    public void setNameServeur(String nameServeur) {
        this.nameServeur = nameServeur;
    }

    public String getCpu() {
        return cpu;
    }

    public Serveur cpu(String cpu) {
        this.cpu = cpu;
        return this;
    }

    public void setCpu(String cpu) {
        this.cpu = cpu;
    }

    public Long getMemory() {
        return memory;
    }

    public Serveur memory(Long memory) {
        this.memory = memory;
        return this;
    }

    public void setMemory(Long memory) {
        this.memory = memory;
    }

    public Long getDisque() {
        return disque;
    }

    public Serveur disque(Long disque) {
        this.disque = disque;
        return this;
    }

    public void setDisque(Long disque) {
        this.disque = disque;
    }

    public String getAvalibity() {
        return avalibity;
    }

    public Serveur avalibity(String avalibity) {
        this.avalibity = avalibity;
        return this;
    }

    public void setAvalibity(String avalibity) {
        this.avalibity = avalibity;
    }

    public Equipe getEquipe() {
        return equipe;
    }

    public Serveur equipe(Equipe equipe) {
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
        if (!(o instanceof Serveur)) {
            return false;
        }
        return id != null && id.equals(((Serveur) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Serveur{" +
            "id=" + getId() +
            ", nameServeur='" + getNameServeur() + "'" +
            ", cpu='" + getCpu() + "'" +
            ", memory=" + getMemory() +
            ", disque=" + getDisque() +
            ", avalibity='" + getAvalibity() + "'" +
            "}";
    }
}
