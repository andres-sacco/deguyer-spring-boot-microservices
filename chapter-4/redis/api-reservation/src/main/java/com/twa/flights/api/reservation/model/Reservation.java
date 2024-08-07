package com.twa.flights.api.reservation.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@RedisHash("reservation")
public class Reservation {

    @Id
    private String id = UUID.randomUUID().toString();

    private String itineraryId;

    private String searchId;

    private List<Passenger> passengers;

    private Contact contact;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getItineraryId() {
        return itineraryId;
    }

    public void setItineraryId(String itineraryId) {
        this.itineraryId = itineraryId;
    }

    public String getSearchId() {
        return searchId;
    }

    public void setSearchId(String searchId) {
        this.searchId = searchId;
    }

    public List<Passenger> getPassengers() {
        return passengers;
    }

    public void setPassengers(List<Passenger> passengers) {
        this.passengers = passengers;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reservation that = (Reservation) o;
        return Objects.equals(id, that.id) && Objects.equals(itineraryId, that.itineraryId) && Objects.equals(searchId, that.searchId) && Objects.equals(passengers, that.passengers) && Objects.equals(contact, that.contact);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, itineraryId, searchId, passengers, contact);
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "id=" + id +
                ", itineraryId='" + itineraryId + '\'' +
                ", searchId='" + searchId + '\'' +
                ", passengers=" + passengers +
                ", contact=" + contact +
                '}';
    }
}
