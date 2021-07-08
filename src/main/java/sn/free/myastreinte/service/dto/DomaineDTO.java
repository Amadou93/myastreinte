package sn.free.myastreinte.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link sn.free.myastreinte.domain.Domaine} entity.
 */
public class DomaineDTO implements Serializable {

    private Long id;

    @NotNull
    private String nom;

    private String nomService;

    private String respService;

    private String telRespService;

    private String emailRespService;

    private String nomDivision;

    private String respDivision;

    private String telRespDivision;

    private String emailRespDivision;

    private String nomDepart;

    private String respDepart;

    private String telRespDepart;

    private String emailRespDepart;

    private String nomDirection;

    private String respDirection;

    private String telDirecteur;

    private String emailDirecteur;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getNomService() {
        return nomService;
    }

    public void setNomService(String nomService) {
        this.nomService = nomService;
    }

    public String getRespService() {
        return respService;
    }

    public void setRespService(String respService) {
        this.respService = respService;
    }

    public String getTelRespService() {
        return telRespService;
    }

    public void setTelRespService(String telRespService) {
        this.telRespService = telRespService;
    }

    public String getEmailRespService() {
        return emailRespService;
    }

    public void setEmailRespService(String emailRespService) {
        this.emailRespService = emailRespService;
    }

    public String getNomDivision() {
        return nomDivision;
    }

    public void setNomDivision(String nomDivision) {
        this.nomDivision = nomDivision;
    }

    public String getRespDivision() {
        return respDivision;
    }

    public void setRespDivision(String respDivision) {
        this.respDivision = respDivision;
    }

    public String getTelRespDivision() {
        return telRespDivision;
    }

    public void setTelRespDivision(String telRespDivision) {
        this.telRespDivision = telRespDivision;
    }

    public String getEmailRespDivision() {
        return emailRespDivision;
    }

    public void setEmailRespDivision(String emailRespDivision) {
        this.emailRespDivision = emailRespDivision;
    }

    public String getNomDepart() {
        return nomDepart;
    }

    public void setNomDepart(String nomDepart) {
        this.nomDepart = nomDepart;
    }

    public String getRespDepart() {
        return respDepart;
    }

    public void setRespDepart(String respDepart) {
        this.respDepart = respDepart;
    }

    public String getTelRespDepart() {
        return telRespDepart;
    }

    public void setTelRespDepart(String telRespDepart) {
        this.telRespDepart = telRespDepart;
    }

    public String getEmailRespDepart() {
        return emailRespDepart;
    }

    public void setEmailRespDepart(String emailRespDepart) {
        this.emailRespDepart = emailRespDepart;
    }

    public String getNomDirection() {
        return nomDirection;
    }

    public void setNomDirection(String nomDirection) {
        this.nomDirection = nomDirection;
    }

    public String getRespDirection() {
        return respDirection;
    }

    public void setRespDirection(String respDirection) {
        this.respDirection = respDirection;
    }

    public String getTelDirecteur() {
        return telDirecteur;
    }

    public void setTelDirecteur(String telDirecteur) {
        this.telDirecteur = telDirecteur;
    }

    public String getEmailDirecteur() {
        return emailDirecteur;
    }

    public void setEmailDirecteur(String emailDirecteur) {
        this.emailDirecteur = emailDirecteur;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        DomaineDTO domaineDTO = (DomaineDTO) o;
        if (domaineDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), domaineDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "DomaineDTO{" +
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
