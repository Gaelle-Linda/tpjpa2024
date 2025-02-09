package models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.ManyToMany;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Entity
public class Concert {

    private Long id;
    private String description;
    private String genreMusical;
    private LocalDate date;          // Date du concert
    private LocalTime heureDebut;    // Heure de début du concert
    private LocalTime heureFin;      // Heure de fin du concert
    private Integer nombrePlaces;
    private Organisateur organisateur;
    private Salle salle;
    private List<Ticket> tickets;
    private List<Artiste> artistes;

    public Concert() {}

    public Concert(String description, String genreMusical, LocalDate date, LocalTime heureDebut, LocalTime heureFin, Integer nombrePlaces) {
        this.description = description;
        this.genreMusical = genreMusical;
        this.date = date;
        this.heureDebut = heureDebut;
        this.heureFin = heureFin;
        this.nombrePlaces = nombrePlaces;
    }

    @Id
    @GeneratedValue
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getGenreMusical() {
        return genreMusical;
    }

    public void setGenreMusical(String genreMusical) {
        this.genreMusical = genreMusical;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getHeureDebut() {
        return heureDebut;
    }

    public void setHeureDebut(LocalTime heureDebut) {
        this.heureDebut = heureDebut;
    }

    public LocalTime getHeureFin() {
        return heureFin;
    }

    public void setHeureFin(LocalTime heureFin) {
        this.heureFin = heureFin;
    }

    public Integer getNombrePlaces() {
        return nombrePlaces;
    }

    public void setNombrePlaces(Integer nombrePlaces) {
        this.nombrePlaces = nombrePlaces;
    }

    @ManyToOne
    public Organisateur getOrganisateur() {
        return organisateur;
    }

    public void setOrganisateur(Organisateur organisateur) {
        this.organisateur = organisateur;
    }

    @ManyToOne
    public Salle getSalle() {
        return salle;
    }

    public void setSalle(Salle salle) {
        this.salle = salle;
    }

    @OneToMany(mappedBy = "concert")
    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }

    @ManyToMany
    public List<Artiste> getArtistes() {
        return artistes;
    }

    public void setArtistes(List<Artiste> artistes) {
        this.artistes = artistes;
    }
}
