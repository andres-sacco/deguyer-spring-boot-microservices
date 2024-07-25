package com.twa.flights.api.reservation.model;

import java.util.Objects;

public class Contact {
        private Long id;
        private String telephoneNumber;
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
