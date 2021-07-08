package sn.free.myastreinte.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

import sn.free.myastreinte.domain.enumeration.State;

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

    @Column(name = "adresse_ip")
    private String adresseIP;

    @Column(name = "composant")
    private String composant;

    @Column(name = "responsable")
    private String responsable;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private State status;

    @Column(name = "equipement_name")
    private String equipementName;

    @Column(name = "message")
    private String message;

    @ManyToOne
    @JsonIgnoreProperties("incidents")
    private Equipe equipe;

    @OneToMany(mappedBy = "incident")
    private Set<Notification> notifications = new HashSet<>();

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

    public String getAdresseIP() {
        return adresseIP;
    }

    public Incident adresseIP(String adresseIP) {
        this.adresseIP = adresseIP;
        return this;
    }

    public void setAdresseIP(String adresseIP) {
        this.adresseIP = adresseIP;
    }

    public String getComposant() {
        return composant;
    }

    public Incident composant(String composant) {
        this.composant = composant;
        return this;
    }

    public void setComposant(String composant) {
        this.composant = composant;
    }

    public String getResponsable() {
        return responsable;
    }

    public Incident responsable(String responsable) {
        this.responsable = responsable;
        return this;
    }

    public void setResponsable(String responsable) {
        this.responsable = responsable;
    }

    public State getStatus() {
        return status;
    }

    public Incident status(State status) {
        this.status = status;
        return this;
    }

    public void setStatus(State status) {
        this.status = status;
    }

    public String getEquipementName() {
        return equipementName;
    }

    public Incident equipementName(String equipementName) {
        this.equipementName = equipementName;
        return this;
    }

    public void setEquipementName(String equipementName) {
        this.equipementName = equipementName;
    }

    public String getMessage() {
        return message;
    }

    public Incident message(String message) {
        this.message = message;
        return this;
    }

    public void setMessage(String message) {
        this.message = message;
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

    public Set<Notification> getNotifications() {
        return notifications;
    }

    public Incident notifications(Set<Notification> notifications) {
        this.notifications = notifications;
        return this;
    }

    public Incident addNotification(Notification notification) {
        this.notifications.add(notification);
        notification.setIncident(this);
        return this;
    }

    public Incident removeNotification(Notification notification) {
        this.notifications.remove(notification);
        notification.setIncident(null);
        return this;
    }

    public void setNotifications(Set<Notification> notifications) {
        this.notifications = notifications;
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
            ", adresseIP='" + getAdresseIP() + "'" +
            ", composant='" + getComposant() + "'" +
            ", responsable='" + getResponsable() + "'" +
            ", status='" + getStatus() + "'" +
            ", equipementName='" + getEquipementName() + "'" +
            ", message='" + getMessage() + "'" +
            "}";
    }
}
