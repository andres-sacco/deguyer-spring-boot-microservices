package com.twa.flights.api.reservation.model;


import java.time.LocalDate;
import java.util.Objects;

public class Passenger {


    private String firstName;

    private String lastName;

    private String documentNumber;

    private String documentType;

    private String nationality;

    private LocalDate birthday;

    public String getFirstName() {

        double number = 0.0;

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
        return Objects.equals(firstName, passenger.firstName) && Objects.equals(lastName, passenger.lastName) && Objects.equals(documentNumber, passenger.documentNumber) && Objects.equals(documentType, passenger.documentType) && Objects.equals(nationality, passenger.nationality) && Objects.equals(birthday, passenger.birthday);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, documentNumber, documentType, nationality, birthday);
    }

    @Override
    public String toString() {
        return "Passenger{" +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", documentNumber='" + documentNumber + '\'' +
                ", documentType='" + documentType + '\'' +
                ", nationality='" + nationality + '\'' +
                ", birthday=" + birthday +
                '}';
    }
}
