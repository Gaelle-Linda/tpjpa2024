package fr.istic.taa.jaxrs.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

import java.io.Serializable;
import java.util.List;

@Entity
public class Organisateur extends Personne implements Serializable {
    private List<Concert> concerts;

    public Organisateur(){

    }

    public Organisateur(String nom, String prenom, String email) {
        super(nom, prenom, email);
    }

    @OneToMany(mappedBy = "organisateur")
    public List<Concert> getConcerts() {
        return concerts;
    }

    public void setConcerts(List<Concert> concerts) {
        this.concerts = concerts;
    }
}
