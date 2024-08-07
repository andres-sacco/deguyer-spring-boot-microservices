package com.twa.flights.api.reservation.model;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "contact") //Only use this annotation if the name is different
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "telephone_number", nullable = false, length = 30)
    private String telephoneNumber;

    @Column(name = "email", nullable = false, length = 30)
    private String email;

        public Long getId() {
                return id;
        }
        public void setId(Long id) {
                this.id = id;
        }

        public String getTelephoneNumber() {
                return telephoneNumber;
        }

        public void setTelephoneNumber(String telephoneNumber) {
                this.telephoneNumber = telephoneNumber;
        }

        public String getEmail() {
                return email;
        }

        public void setEmail(String email) {
                this.email = email;
        }

        @Override
        public boolean equals(Object o) {
                if (this == o) return true;
                if (o == null || getClass() != o.getClass()) return false;
                Contact contact = (Contact) o;
                return Objects.equals(id, contact.id) && Objects.equals(telephoneNumber, contact.telephoneNumber) && Objects.equals(email, contact.email);
        }

        @Override
        public int hashCode() {
                return Objects.hash(id, telephoneNumber, email);
        }

        @Override
        public String toString() {
                return "Contact{" +
                        "id=" + id +
                        ", telephoneNumber='" + telephoneNumber + '\'' +
                        ", email='" + email + '\'' +
                        '}';
        }
}
