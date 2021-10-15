package hospital_database;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "patients")
public class Patient {
    private long id;
    private String firstName;
    private String lastName;
    private String email;
    private Date dateOfBirth;
    private String picture;
    private boolean hasMedicalInsurance;
    private Set<Visitation> visitations;
    private Diagnose diagnose;
    private Set<PrescribedMedicament> prescribedMedicaments;

    public Patient() {
    }

    public Patient(String firstName, String lastName, String email, Date dateOfBirth, String picture, boolean hasMedicalInsurance) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
        this.picture = picture;
        this.hasMedicalInsurance = hasMedicalInsurance;
        this.visitations = new HashSet<>();
        this.prescribedMedicaments = new HashSet<>();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Column(name = "first_name", nullable = false, length = 50)
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Column(name = "last_name", nullable = false, length = 60)
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(name = "date_of_birth")
    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    @Column(columnDefinition = "BLOB")
    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    @Column(name = "medical_insurance")
    public boolean hasMedicalInsurance() {
        return hasMedicalInsurance;
    }

    public void setMedicalInsurance(boolean hasMedicalInsurance) {
        this.hasMedicalInsurance = hasMedicalInsurance;
    }

    @OneToMany(mappedBy = "patient", cascade = CascadeType.PERSIST)
    public Set<Visitation> getVisitations() {
        return visitations;
    }

    public void setVisitations(Set<Visitation> visitations) {
        this.visitations = visitations;
    }

    @OneToOne(mappedBy = "patient")
    public Diagnose getDiagnose() {
        return diagnose;
    }

    public void setDiagnose(Diagnose diagnose) {
        this.diagnose = diagnose;
    }

    @ManyToMany(mappedBy = "patients", cascade = CascadeType.PERSIST)
    @Column(name = "prescribed_medicaments")
    public Set<PrescribedMedicament> getPrescribedMedicaments() {
        return prescribedMedicaments;
    }

    public void setPrescribedMedicaments(Set<PrescribedMedicament> prescribedMedicaments) {
        this.prescribedMedicaments = prescribedMedicaments;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Patient patient = (Patient) o;
        return id == patient.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Patient:" + System.lineSeparator() +
                "id = " + id + System.lineSeparator() +
                "firstName = " + firstName + System.lineSeparator() +
                "lastName = " + lastName + System.lineSeparator() +
                "email = " + email + System.lineSeparator() +
                "dateOfBirth = " + dateOfBirth + System.lineSeparator() +
                "picture = " + picture + System.lineSeparator() +
                "hasMedicalInsurance = " + hasMedicalInsurance + System.lineSeparator() +
                "visitations = " + visitations + System.lineSeparator() +
                "diagnose = " + diagnose + System.lineSeparator() +
                "prescribedMedicaments = " + prescribedMedicaments;
    }
}