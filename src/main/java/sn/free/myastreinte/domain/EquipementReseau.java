package sn.free.myastreinte.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;

/**
 * A EquipementReseau.
 */
@Entity
@Table(name = "equipement_reseau")
public class EquipementReseau implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min = 3)
    @Column(name = "equipement_name")
    private String equipementName;

    @Size(min = 3)
    @Column(name = "type")
    private String type;

    @Column(name = "ip_address")
    private Long ipAddress;

    @Column(name = "version")
    private Long version;

    @ManyToOne
    @JsonIgnoreProperties("equipementReseaus")
    private Equipe equipe;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEquipementName() {
        return equipementName;
    }

    public EquipementReseau equipementName(String equipementName) {
        this.equipementName = equipementName;
        return this;
    }

    public void setEquipementName(String equipementName) {
        this.equipementName = equipementName;
    }

    public String getType() {
        return type;
    }

    public EquipementReseau type(String type) {
        this.type = type;
        return this;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getIpAddress() {
        return ipAddress;
    }

    public EquipementReseau ipAddress(Long ipAddress) {
        this.ipAddress = ipAddress;
        return this;
    }

    public void setIpAddress(Long ipAddress) {
        this.ipAddress = ipAddress;
    }

    public Long getVersion() {
        return version;
    }

    public EquipementReseau version(Long version) {
        this.version = version;
        return this;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public Equipe getEquipe() {
        return equipe;
    }

    public EquipementReseau equipe(Equipe equipe) {
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
        if (!(o instanceof EquipementReseau)) {
            return false;
        }
        return id != null && id.equals(((EquipementReseau) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "EquipementReseau{" +
            "id=" + getId() +
            ", equipementName='" + getEquipementName() + "'" +
            ", type='" + getType() + "'" +
            ", ipAddress=" + getIpAddress() +
            ", version=" + getVersion() +
            "}";
    }
}
