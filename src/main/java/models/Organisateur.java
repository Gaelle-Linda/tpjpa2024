package models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.util.List;

@Entity
public class Organisateur extends Personne {
    private List<Concert> concerts;

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
