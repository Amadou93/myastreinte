package sn.free.myastreinte.domain;

import javax.persistence.*;

import java.io.Serializable;

/**
 * A Domaine.
 */
@Entity
@Table(name = "domaine")
public class Domaine implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nom")
    private String nom;

    @Column(name = "service")
    private String service;

    @Column(name = "responsable_service")
    private String responsableService;

    @Column(name = "num_tel_responsable_service")
    private String numTelResponsableService;

    @Column(name = "email_responsable_service")
    private String emailResponsableService;

    @Column(name = "division")
    private String division;

    @Column(name = "responsable_division")
    private String responsableDivision;

    @Column(name = "num_responsable_division")
    private String numResponsableDivision;

    @Column(name = "email_responsable_division")
    private String emailResponsableDivision;

    @Column(name = "departement")
    private String departement;

    @Column(name = "responsable_departement")
    private String responsableDepartement;

    @Column(name = "numero_tel_responsable_departement")
    private String numeroTelResponsableDepartement;

    @Column(name = "email_responsable_departement")
    private String emailResponsableDepartement;

    @Column(name = "direction")
    private String direction;

    @Column(name = "responsable_direction")
    private String responsableDirection;

    @Column(name = "numero_tel_directeur")
    private String numeroTelDirecteur;

    @Column(name = "email_directeur")
    private String emailDirecteur;

    // jhipster-needle-entity-add-field -mvn JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public Domaine nom(String nom) {
        this.nom = nom;
        return this;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getService() {
        return service;
    }

    public Domaine service(String service) {
        this.service = service;
        return this;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getResponsableService() {
        return responsableService;
    }

    public Domaine responsableService(String responsableService) {
        this.responsableService = responsableService;
        return this;
    }

    public void setResponsableService(String responsableService) {
        this.responsableService = responsableService;
    }

    public String getNumTelResponsableService() {
        return numTelResponsableService;
    }

    public Domaine numTelResponsableService(String numTelResponsableService) {
        this.numTelResponsableService = numTelResponsableService;
        return this;
    }

    public void setNumTelResponsableService(String numTelResponsableService) {
        this.numTelResponsableService = numTelResponsableService;
    }

    public String getEmailResponsableService() {
        return emailResponsableService;
    }

    public Domaine emailResponsableService(String emailResponsableService) {
        this.emailResponsableService = emailResponsableService;
        return this;
    }

    public void setEmailResponsableService(String emailResponsableService) {
        this.emailResponsableService = emailResponsableService;
    }

    public String getDivision() {
        return division;
    }

    public Domaine division(String division) {
        this.division = division;
        return this;
    }

    public void setDivision(String division) {
        this.division = division;
    }

    public String getResponsableDivision() {
        return responsableDivision;
    }

    public Domaine responsableDivision(String responsableDivision) {
        this.responsableDivision = responsableDivision;
        return this;
    }

    public void setResponsableDivision(String responsableDivision) {
        this.responsableDivision = responsableDivision;
    }

    public String getNumResponsableDivision() {
        return numResponsableDivision;
    }

    public Domaine numResponsableDivision(String numResponsableDivision) {
        this.numResponsableDivision = numResponsableDivision;
        return this;
    }

    public void setNumResponsableDivision(String numResponsableDivision) {
        this.numResponsableDivision = numResponsableDivision;
    }

    public String getEmailResponsableDivision() {
        return emailResponsableDivision;
    }

    public Domaine emailResponsableDivision(String emailResponsableDivision) {
        this.emailResponsableDivision = emailResponsableDivision;
        return this;
    }

    public void setEmailResponsableDivision(String emailResponsableDivision) {
        this.emailResponsableDivision = emailResponsableDivision;
    }

    public String getDepartement() {
        return departement;
    }

    public Domaine departement(String departement) {
        this.departement = departement;
        return this;
    }

    public void setDepartement(String departement) {
        this.departement = departement;
    }

    public String getResponsableDepartement() {
        return responsableDepartement;
    }

    public Domaine responsableDepartement(String responsableDepartement) {
        this.responsableDepartement = responsableDepartement;
        return this;
    }

    public void setResponsableDepartement(String responsableDepartement) {
        this.responsableDepartement = responsableDepartement;
    }

    public String getNumeroTelResponsableDepartement() {
        return numeroTelResponsableDepartement;
    }

    public Domaine numeroTelResponsableDepartement(String numeroTelResponsableDepartement) {
        this.numeroTelResponsableDepartement = numeroTelResponsableDepartement;
        return this;
    }

    public void setNumeroTelResponsableDepartement(String numeroTelResponsableDepartement) {
        this.numeroTelResponsableDepartement = numeroTelResponsableDepartement;
    }

    public String getEmailResponsableDepartement() {
        return emailResponsableDepartement;
    }

    public Domaine emailResponsableDepartement(String emailResponsableDepartement) {
        this.emailResponsableDepartement = emailResponsableDepartement;
        return this;
    }

    public void setEmailResponsableDepartement(String emailResponsableDepartement) {
        this.emailResponsableDepartement = emailResponsableDepartement;
    }

    public String getDirection() {
        return direction;
    }

    public Domaine direction(String direction) {
        this.direction = direction;
        return this;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getResponsableDirection() {
        return responsableDirection;
    }

    public Domaine responsableDirection(String responsableDirection) {
        this.responsableDirection = responsableDirection;
        return this;
    }

    public void setResponsableDirection(String responsableDirection) {
        this.responsableDirection = responsableDirection;
    }

    public String getNumeroTelDirecteur() {
        return numeroTelDirecteur;
    }

    public Domaine numeroTelDirecteur(String numeroTelDirecteur) {
        this.numeroTelDirecteur = numeroTelDirecteur;
        return this;
    }

    public void setNumeroTelDirecteur(String numeroTelDirecteur) {
        this.numeroTelDirecteur = numeroTelDirecteur;
    }

    public String getEmailDirecteur() {
        return emailDirecteur;
    }

    public Domaine emailDirecteur(String emailDirecteur) {
        this.emailDirecteur = emailDirecteur;
        return this;
    }

    public void setEmailDirecteur(String emailDirecteur) {
        this.emailDirecteur = emailDirecteur;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Domaine)) {
            return false;
        }
        return id != null && id.equals(((Domaine) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Domaine{" +
            "id=" + getId() +
            ", nom='" + getNom() + "'" +
            ", service='" + getService() + "'" +
            ", responsableService='" + getResponsableService() + "'" +
            ", numTelResponsableService='" + getNumTelResponsableService() + "'" +
            ", emailResponsableService='" + getEmailResponsableService() + "'" +
            ", division='" + getDivision() + "'" +
            ", responsableDivision='" + getResponsableDivision() + "'" +
            ", numResponsableDivision='" + getNumResponsableDivision() + "'" +
            ", emailResponsableDivision='" + getEmailResponsableDivision() + "'" +
            ", departement='" + getDepartement() + "'" +
            ", responsableDepartement='" + getResponsableDepartement() + "'" +
            ", numeroTelResponsableDepartement='" + getNumeroTelResponsableDepartement() + "'" +
            ", emailResponsableDepartement='" + getEmailResponsableDepartement() + "'" +
            ", direction='" + getDirection() + "'" +
            ", responsableDirection='" + getResponsableDirection() + "'" +
            ", numeroTelDirecteur='" + getNumeroTelDirecteur() + "'" +
            ", emailDirecteur='" + getEmailDirecteur() + "'" +
            "}";
    }
}
