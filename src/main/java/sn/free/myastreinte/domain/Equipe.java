package sn.free.myastreinte.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A Equipe.
 */
@Entity
@Table(name = "equipe")
public class Equipe implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(min = 3)
    @Column(name = "name", nullable = false)
    private String name;

    @OneToMany(mappedBy = "equipe")
    private Set<Employe> employes = new HashSet<>();

    @OneToMany(mappedBy = "equipe")
    private Set<Serveur> serveurs = new HashSet<>();

    @OneToMany(mappedBy = "equipe")
    private Set<EquipementReseau> equipements = new HashSet<>();

    @OneToMany(mappedBy = "equipe")
    private Set<Switche> switches = new HashSet<>();

    @OneToMany(mappedBy = "equipe")
    private Set<Incident> dates = new HashSet<>();

    @OneToMany(mappedBy = "equipe")
    private Set<Application> applications = new HashSet<>();

    @OneToMany(mappedBy = "equipe")
    private Set<Basededonnee> basededonnees = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties("equipes")
    private Division division;

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

    public Equipe name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Employe> getEmployes() {
        return employes;
    }

    public Equipe employes(Set<Employe> employes) {
        this.employes = employes;
        return this;
    }

    public Equipe addEmploye(Employe employe) {
        this.employes.add(employe);
        employe.setEquipe(this);
        return this;
    }

    public Equipe removeEmploye(Employe employe) {
        this.employes.remove(employe);
        employe.setEquipe(null);
        return this;
    }

    public void setEmployes(Set<Employe> employes) {
        this.employes = employes;
    }

    public Set<Serveur> getServeurs() {
        return serveurs;
    }

    public Equipe serveurs(Set<Serveur> serveurs) {
        this.serveurs = serveurs;
        return this;
    }

    public Equipe addServeur(Serveur serveur) {
        this.serveurs.add(serveur);
        serveur.setEquipe(this);
        return this;
    }

    public Equipe removeServeur(Serveur serveur) {
        this.serveurs.remove(serveur);
        serveur.setEquipe(null);
        return this;
    }

    public void setServeurs(Set<Serveur> serveurs) {
        this.serveurs = serveurs;
    }

    public Set<EquipementReseau> getEquipements() {
        return equipements;
    }

    public Equipe equipements(Set<EquipementReseau> equipementReseaus) {
        this.equipements = equipementReseaus;
        return this;
    }

    public Equipe addEquipement(EquipementReseau equipementReseau) {
        this.equipements.add(equipementReseau);
        equipementReseau.setEquipe(this);
        return this;
    }

    public Equipe removeEquipement(EquipementReseau equipementReseau) {
        this.equipements.remove(equipementReseau);
        equipementReseau.setEquipe(null);
        return this;
    }

    public void setEquipements(Set<EquipementReseau> equipementReseaus) {
        this.equipements = equipementReseaus;
    }

    public Set<Switche> getSwitches() {
        return switches;
    }

    public Equipe switches(Set<Switche> switches) {
        this.switches = switches;
        return this;
    }

    public Equipe addSwitche(Switche switche) {
        this.switches.add(switche);
        switche.setEquipe(this);
        return this;
    }

    public Equipe removeSwitche(Switche switche) {
        this.switches.remove(switche);
        switche.setEquipe(null);
        return this;
    }

    public void setSwitches(Set<Switche> switches) {
        this.switches = switches;
    }

    public Set<Incident> getDates() {
        return dates;
    }

    public Equipe dates(Set<Incident> incidents) {
        this.dates = incidents;
        return this;
    }

    public Equipe addDate(Incident incident) {
        this.dates.add(incident);
        incident.setEquipe(this);
        return this;
    }

    public Equipe removeDate(Incident incident) {
        this.dates.remove(incident);
        incident.setEquipe(null);
        return this;
    }

    public void setDates(Set<Incident> incidents) {
        this.dates = incidents;
    }

    public Set<Application> getApplications() {
        return applications;
    }

    public Equipe applications(Set<Application> applications) {
        this.applications = applications;
        return this;
    }

    public Equipe addApplication(Application application) {
        this.applications.add(application);
        application.setEquipe(this);
        return this;
    }

    public Equipe removeApplication(Application application) {
        this.applications.remove(application);
        application.setEquipe(null);
        return this;
    }

    public void setApplications(Set<Application> applications) {
        this.applications = applications;
    }

    public Set<Basededonnee> getBasededonnees() {
        return basededonnees;
    }

    public Equipe basededonnees(Set<Basededonnee> basededonnees) {
        this.basededonnees = basededonnees;
        return this;
    }

    public Equipe addBasededonnee(Basededonnee basededonnee) {
        this.basededonnees.add(basededonnee);
        basededonnee.setEquipe(this);
        return this;
    }

    public Equipe removeBasededonnee(Basededonnee basededonnee) {
        this.basededonnees.remove(basededonnee);
        basededonnee.setEquipe(null);
        return this;
    }

    public void setBasededonnees(Set<Basededonnee> basededonnees) {
        this.basededonnees = basededonnees;
    }

    public Division getDivision() {
        return division;
    }

    public Equipe division(Division division) {
        this.division = division;
        return this;
    }

    public void setDivision(Division division) {
        this.division = division;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Equipe)) {
            return false;
        }
        return id != null && id.equals(((Equipe) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Equipe{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            "}";
    }
}
