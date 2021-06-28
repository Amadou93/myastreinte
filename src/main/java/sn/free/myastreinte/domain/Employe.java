package sn.free.myastreinte.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A Employe.
 */
@Entity
@Table(name = "employe")
public class Employe implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "matricul", nullable = false)
    private Long matricul;

    @Size(min = 3)
    @Column(name = "first_name")
    private String firstName;

    @Size(min = 3)
    @Column(name = "last_name")
    private String lastName;

    @Size(min = 3)
    @Column(name = "email")
    private String email;

    @Column(name = "phone_number")
    private Long phoneNumber;

    @OneToMany(mappedBy = "employe")
    private Set<Astreinte> astreintes = new HashSet<>();

    @OneToMany(mappedBy = "employe")
    private Set<Notification> notifications = new HashSet<>();

    @OneToMany(mappedBy = "employe")
    private Set<Absence> absences = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties("employes")
    private Equipe equipe;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMatricul() {
        return matricul;
    }

    public Employe matricul(Long matricul) {
        this.matricul = matricul;
        return this;
    }

    public void setMatricul(Long matricul) {
        this.matricul = matricul;
    }

    public String getFirstName() {
        return firstName;
    }

    public Employe firstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Employe lastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public Employe email(String email) {
        this.email = email;
        return this;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getPhoneNumber() {
        return phoneNumber;
    }

    public Employe phoneNumber(Long phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public void setPhoneNumber(Long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Set<Astreinte> getAstreintes() {
        return astreintes;
    }

    public Employe astreintes(Set<Astreinte> astreintes) {
        this.astreintes = astreintes;
        return this;
    }

    public Employe addAstreinte(Astreinte astreinte) {
        this.astreintes.add(astreinte);
        astreinte.setEmploye(this);
        return this;
    }

    public Employe removeAstreinte(Astreinte astreinte) {
        this.astreintes.remove(astreinte);
        astreinte.setEmploye(null);
        return this;
    }

    public void setAstreintes(Set<Astreinte> astreintes) {
        this.astreintes = astreintes;
    }

    public Set<Notification> getNotifications() {
        return notifications;
    }

    public Employe notifications(Set<Notification> notifications) {
        this.notifications = notifications;
        return this;
    }

    public Employe addNotification(Notification notification) {
        this.notifications.add(notification);
        notification.setEmploye(this);
        return this;
    }

    public Employe removeNotification(Notification notification) {
        this.notifications.remove(notification);
        notification.setEmploye(null);
        return this;
    }

    public void setNotifications(Set<Notification> notifications) {
        this.notifications = notifications;
    }

    public Set<Absence> getAbsences() {
        return absences;
    }

    public Employe absences(Set<Absence> absences) {
        this.absences = absences;
        return this;
    }

    public Employe addAbsence(Absence absence) {
        this.absences.add(absence);
        absence.setEmploye(this);
        return this;
    }

    public Employe removeAbsence(Absence absence) {
        this.absences.remove(absence);
        absence.setEmploye(null);
        return this;
    }

    public void setAbsences(Set<Absence> absences) {
        this.absences = absences;
    }

    public Equipe getEquipe() {
        return equipe;
    }

    public Employe equipe(Equipe equipe) {
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
        if (!(o instanceof Employe)) {
            return false;
        }
        return id != null && id.equals(((Employe) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Employe{" +
            "id=" + getId() +
            ", matricul=" + getMatricul() +
            ", firstName='" + getFirstName() + "'" +
            ", lastName='" + getLastName() + "'" +
            ", email='" + getEmail() + "'" +
            ", phoneNumber=" + getPhoneNumber() +
            "}";
    }
}
