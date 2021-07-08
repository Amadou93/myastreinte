package sn.free.myastreinte.domain;

import javax.persistence.*;
import javax.validation.constraints.*;

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

    @NotNull
    @Column(name = "nom", nullable = false)
    private String nom;

    @Column(name = "nom_service")
    private String nomService;

    @Column(name = "resp_service")
    private String respService;

    @Column(name = "tel_resp_service")
    private String telRespService;

    @Column(name = "email_resp_service")
    private String emailRespService;

    @Column(name = "nom_division")
    private String nomDivision;

    @Column(name = "resp_division")
    private String respDivision;

    @Column(name = "tel_resp_division")
    private String telRespDivision;

    @Column(name = "email_resp_division")
    private String emailRespDivision;

    @Column(name = "nom_depart")
    private String nomDepart;

    @Column(name = "resp_depart")
    private String respDepart;

    @Column(name = "tel_resp_depart")
    private String telRespDepart;

    @Column(name = "email_resp_depart")
    private String emailRespDepart;

    @Column(name = "nom_direction")
    private String nomDirection;

    @Column(name = "resp_direction")
    private String respDirection;

    @Column(name = "tel_directeur")
    private String telDirecteur;

    @Column(name = "email_directeur")
    private String emailDirecteur;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
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

    public String getNomService() {
        return nomService;
    }

    public Domaine nomService(String nomService) {
        this.nomService = nomService;
        return this;
    }

    public void setNomService(String nomService) {
        this.nomService = nomService;
    }

    public String getRespService() {
        return respService;
    }

    public Domaine respService(String respService) {
        this.respService = respService;
        return this;
    }

    public void setRespService(String respService) {
        this.respService = respService;
    }

    public String getTelRespService() {
        return telRespService;
    }

    public Domaine telRespService(String telRespService) {
        this.telRespService = telRespService;
        return this;
    }

    public void setTelRespService(String telRespService) {
        this.telRespService = telRespService;
    }

    public String getEmailRespService() {
        return emailRespService;
    }

    public Domaine emailRespService(String emailRespService) {
        this.emailRespService = emailRespService;
        return this;
    }

    public void setEmailRespService(String emailRespService) {
        this.emailRespService = emailRespService;
    }

    public String getNomDivision() {
        return nomDivision;
    }

    public Domaine nomDivision(String nomDivision) {
        this.nomDivision = nomDivision;
        return this;
    }

    public void setNomDivision(String nomDivision) {
        this.nomDivision = nomDivision;
    }

    public String getRespDivision() {
        return respDivision;
    }

    public Domaine respDivision(String respDivision) {
        this.respDivision = respDivision;
        return this;
    }

    public void setRespDivision(String respDivision) {
        this.respDivision = respDivision;
    }

    public String getTelRespDivision() {
        return telRespDivision;
    }

    public Domaine telRespDivision(String telRespDivision) {
        this.telRespDivision = telRespDivision;
        return this;
    }

    public void setTelRespDivision(String telRespDivision) {
        this.telRespDivision = telRespDivision;
    }

    public String getEmailRespDivision() {
        return emailRespDivision;
    }

    public Domaine emailRespDivision(String emailRespDivision) {
        this.emailRespDivision = emailRespDivision;
        return this;
    }

    public void setEmailRespDivision(String emailRespDivision) {
        this.emailRespDivision = emailRespDivision;
    }

    public String getNomDepart() {
        return nomDepart;
    }

    public Domaine nomDepart(String nomDepart) {
        this.nomDepart = nomDepart;
        return this;
    }

    public void setNomDepart(String nomDepart) {
        this.nomDepart = nomDepart;
    }

    public String getRespDepart() {
        return respDepart;
    }

    public Domaine respDepart(String respDepart) {
        this.respDepart = respDepart;
        return this;
    }

    public void setRespDepart(String respDepart) {
        this.respDepart = respDepart;
    }

    public String getTelRespDepart() {
        return telRespDepart;
    }

    public Domaine telRespDepart(String telRespDepart) {
        this.telRespDepart = telRespDepart;
        return this;
    }

    public void setTelRespDepart(String telRespDepart) {
        this.telRespDepart = telRespDepart;
    }

    public String getEmailRespDepart() {
        return emailRespDepart;
    }

    public Domaine emailRespDepart(String emailRespDepart) {
        this.emailRespDepart = emailRespDepart;
        return this;
    }

    public void setEmailRespDepart(String emailRespDepart) {
        this.emailRespDepart = emailRespDepart;
    }

    public String getNomDirection() {
        return nomDirection;
    }

    public Domaine nomDirection(String nomDirection) {
        this.nomDirection = nomDirection;
        return this;
    }

    public void setNomDirection(String nomDirection) {
        this.nomDirection = nomDirection;
    }

    public String getRespDirection() {
        return respDirection;
    }

    public Domaine respDirection(String respDirection) {
        this.respDirection = respDirection;
        return this;
    }

    public void setRespDirection(String respDirection) {
        this.respDirection = respDirection;
    }

    public String getTelDirecteur() {
        return telDirecteur;
    }

    public Domaine telDirecteur(String telDirecteur) {
        this.telDirecteur = telDirecteur;
        return this;
    }

    public void setTelDirecteur(String telDirecteur) {
        this.telDirecteur = telDirecteur;
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
            ", nomService='" + getNomService() + "'" +
            ", respService='" + getRespService() + "'" +
            ", telRespService='" + getTelRespService() + "'" +
            ", emailRespService='" + getEmailRespService() + "'" +
            ", nomDivision='" + getNomDivision() + "'" +
            ", respDivision='" + getRespDivision() + "'" +
            ", telRespDivision='" + getTelRespDivision() + "'" +
            ", emailRespDivision='" + getEmailRespDivision() + "'" +
            ", nomDepart='" + getNomDepart() + "'" +
            ", respDepart='" + getRespDepart() + "'" +
            ", telRespDepart='" + getTelRespDepart() + "'" +
            ", emailRespDepart='" + getEmailRespDepart() + "'" +
            ", nomDirection='" + getNomDirection() + "'" +
            ", respDirection='" + getRespDirection() + "'" +
            ", telDirecteur='" + getTelDirecteur() + "'" +
            ", emailDirecteur='" + getEmailDirecteur() + "'" +
            "}";
    }
}
