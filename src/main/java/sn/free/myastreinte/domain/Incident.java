package sn.free.myastreinte.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.Instant;

/**
 * A Incident.
 */
@Entity
@Table(name = "incident")
public class Incident implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "date", nullable = false)
    private Instant date;

    @NotNull
    @Size(min = 3)
    @Column(name = "type", nullable = false)
    private String type;

    @Size(min = 3)
    @Column(name = "criticite")
    private String criticite;

    @Column(name = "sla")
    private Long sla;

    @Size(min = 3)
    @Column(name = "description")
    private String description;

    @ManyToOne
    @JsonIgnoreProperties("incidents")
    private Equipe equipe;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instant getDate() {
        return date;
    }

    public Incident date(Instant date) {
        this.date = date;
        return this;
    }

    public void setDate(Instant date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public Incident type(String type) {
        this.type = type;
        return this;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCriticite() {
        return criticite;
    }

    public Incident criticite(String criticite) {
        this.criticite = criticite;
        return this;
    }

    public void setCriticite(String criticite) {
        this.criticite = criticite;
    }

    public Long getSla() {
        return sla;
    }

    public Incident sla(Long sla) {
        this.sla = sla;
        return this;
    }

    public void setSla(Long sla) {
        this.sla = sla;
    }

    public String getDescription() {
        return description;
    }

    public Incident description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Equipe getEquipe() {
        return equipe;
    }

    public Incident equipe(Equipe equipe) {
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
        if (!(o instanceof Incident)) {
            return false;
        }
        return id != null && id.equals(((Incident) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Incident{" +
            "id=" + getId() +
            ", date='" + getDate() + "'" +
            ", type='" + getType() + "'" +
            ", criticite='" + getCriticite() + "'" +
            ", sla=" + getSla() +
            ", description='" + getDescription() + "'" +
            "}";
    }
}
