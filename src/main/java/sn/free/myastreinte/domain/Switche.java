package sn.free.myastreinte.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;

/**
 * A Switche.
 */
@Entity
@Table(name = "switche")
public class Switche implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "ip_address", nullable = false)
    private Long ipAddress;

    @ManyToOne
    @JsonIgnoreProperties("switches")
    private Equipe equipe;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIpAddress() {
        return ipAddress;
    }

    public Switche ipAddress(Long ipAddress) {
        this.ipAddress = ipAddress;
        return this;
    }

    public void setIpAddress(Long ipAddress) {
        this.ipAddress = ipAddress;
    }

    public Equipe getEquipe() {
        return equipe;
    }

    public Switche equipe(Equipe equipe) {
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
        if (!(o instanceof Switche)) {
            return false;
        }
        return id != null && id.equals(((Switche) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Switche{" +
            "id=" + getId() +
            ", ipAddress=" + getIpAddress() +
            "}";
    }
}
