package fr.istic.taa.jaxrs.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Client extends Personne implements Serializable {
    private List<Ticket> tickets;

    // Constructeur sans argument requis par Hibernate
    public Client() {}

    public Client(String nom, String prenom, String email) {

       super(nom, prenom, email);
    }

    @OneToMany(mappedBy = "client")
    @JsonManagedReference
    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }
}