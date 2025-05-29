package com.twa.flights.api.reservation.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "passenger") // Only use this annotation if the name is different
public class Passenger {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name", nullable = false, length = 10)
    private String firstName;

    @Column(name = "last_name", nullable = false, length = 10)
    private String lastName;

    @Column(name = "document_number", nullable = false, length = 10)
    private String documentNumber;

    @Column(name = "document_type", nullable = false, length = 10)
    private String documentType;

    @Column(name = "nationality", nullable = false, length = 3)
    private String nationality;

    @Column(name = "birthday", nullable = false)
    private LocalDate birthday;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDocumentNumber() {
        return documentNumber;
    }

    public void setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
    }

    public String getDocumentType() {
        return documentType;
    }

    public void setDocumentType(String documentType) {
        this.documentType = documentType;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Passenger passenger = (Passenger) o;
        return Objects.equals(id, passenger.id)
                && Objects.equals(firstName, passenger.firstName)
                && Objects.equals(lastName, passenger.lastName)
                && Objects.equals(documentNumber, passenger.documentNumber)
                && Objects.equals(documentType, passenger.documentType)
                && Objects.equals(nationality, passenger.nationality)
                && Objects.equals(birthday, passenger.birthday);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                id, firstName, lastName, documentNumber, documentType, nationality, birthday);
    }

    @Override
    public String toString() {
        return "Passenger{"
                + "id="
                + id
                + ", firstName='"
                + firstName
                + '\''
                + ", lastName='"
                + lastName
                + '\''
                + ", documentNumber='"
                + documentNumber
                + '\''
                + ", documentType='"
                + documentType
                + '\''
                + ", nationality='"
                + nationality
                + '\''
                + ", birthday="
                + birthday
                + '}';
    }
}
