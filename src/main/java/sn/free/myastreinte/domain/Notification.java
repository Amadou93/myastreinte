package sn.free.myastreinte.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.Instant;

import sn.free.myastreinte.domain.enumeration.State;

/**
 * A Notification.
 */
@Entity
@Table(name = "notification")
public class Notification implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "date", nullable = false)
    private Instant date;

    @NotNull
    @Column(name = "type", nullable = false)
    private String type;

    @Column(name = "level")
    private Long level;

    @Size(min = 3)
    @Column(name = "dispositif_name")
    private String dispositifName;

    @Enumerated(EnumType.STRING)
    @Column(name = "state")
    private State state;

    @Size(min = 3)
    @Column(name = "groupe")
    private String groupe;

    @Size(min = 3)
    @Column(name = "astreinte_name")
    private String astreinteName;

    @Column(name = "availiblity")
    private Instant availiblity;

    @ManyToOne
    @JsonIgnoreProperties("notifications")
    private Employe employe;

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

    public Notification date(Instant date) {
        this.date = date;
        return this;
    }

    public void setDate(Instant date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public Notification type(String type) {
        this.type = type;
        return this;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getLevel() {
        return level;
    }

    public Notification level(Long level) {
        this.level = level;
        return this;
    }

    public void setLevel(Long level) {
        this.level = level;
    }

    public String getDispositifName() {
        return dispositifName;
    }

    public Notification dispositifName(String dispositifName) {
        this.dispositifName = dispositifName;
        return this;
    }

    public void setDispositifName(String dispositifName) {
        this.dispositifName = dispositifName;
    }

    public State getState() {
        return state;
    }

    public Notification state(State state) {
        this.state = state;
        return this;
    }

    public void setState(State state) {
        this.state = state;
    }

    public String getGroupe() {
        return groupe;
    }

    public Notification groupe(String groupe) {
        this.groupe = groupe;
        return this;
    }

    public void setGroupe(String groupe) {
        this.groupe = groupe;
    }

    public String getAstreinteName() {
        return astreinteName;
    }

    public Notification astreinteName(String astreinteName) {
        this.astreinteName = astreinteName;
        return this;
    }

    public void setAstreinteName(String astreinteName) {
        this.astreinteName = astreinteName;
    }

    public Instant getAvailiblity() {
        return availiblity;
    }

    public Notification availiblity(Instant availiblity) {
        this.availiblity = availiblity;
        return this;
    }

    public void setAvailiblity(Instant availiblity) {
        this.availiblity = availiblity;
    }

    public Employe getEmploye() {
        return employe;
    }

    public Notification employe(Employe employe) {
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
        if (!(o instanceof Notification)) {
            return false;
        }
        return id != null && id.equals(((Notification) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Notification{" +
            "id=" + getId() +
            ", date='" + getDate() + "'" +
            ", type='" + getType() + "'" +
            ", level=" + getLevel() +
            ", dispositifName='" + getDispositifName() + "'" +
            ", state='" + getState() + "'" +
            ", groupe='" + getGroupe() + "'" +
            ", astreinteName='" + getAstreinteName() + "'" +
            ", availiblity='" + getAvailiblity() + "'" +
            "}";
    }
}
