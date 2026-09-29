package org.example.kinobackend.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
public class Reservation {
    @Id
    private int id;
    private String customerName;
    private String customerPhone;
    private String customerMail;
    @OneToMany(mappedBy = "reservation", cascade = CascadeType.ALL)
    @JsonBackReference
    private Set<Ticket> tickets = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "showing", referencedColumnName = "id")
    private Showing showings;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    public String getCustomerMail() {
        return customerMail;
    }

    public void setCustomerMail(String customerMail) {
        this.customerMail = customerMail;
    }

    public Set<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(Set<Ticket> tickets) {
        this.tickets = tickets;
    }

    public Showing getShowings() {
        return showings;
    }

    public void setShowings(Showing showings) {
        this.showings = showings;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Reservation that = (Reservation) o;
        return id == that.id && Objects.equals(customerName, that.customerName) && Objects.equals(customerPhone, that.customerPhone) && Objects.equals(customerMail, that.customerMail) && Objects.equals(showings, that.showings);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, customerName, customerPhone, customerMail);
    }
}
