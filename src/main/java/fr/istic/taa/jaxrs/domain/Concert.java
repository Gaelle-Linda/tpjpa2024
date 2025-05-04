package fr.istic.taa.jaxrs.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Concert implements Serializable {

    // Identifiant unique du concert
    private Long id;

    // Description du concert
    private String description;

    // Genre musical (ex : Jazz, Rock, Classique…)
    private String genreMusical;

    // Date du concert (jour uniquement)
    @Temporal(TemporalType.DATE)
    private Date date;

    // Heure de début du concert
    @Temporal(TemporalType.TIME)
    private Date heureDebut;

    // Heure de fin du concert
    @Temporal(TemporalType.TIME)
    private Date heureFin;

    // Nombre de places disponibles
    private Integer nombrePlaces;

    // Organisateur du concert
    private Organisateur organisateur;

    // Salle où se déroule le concert
    private Salle salle;

    // Liste des tickets associés
    @JsonManagedReference
    private List<Ticket> tickets;

    // Liste des artistes participants
    private List<Artiste> artistes;

    // Constructeur par défaut (requis par JPA)
    public Concert() {}

    // Constructeur avec tous les paramètres importants
    public Concert(String description, String genreMusical, Date date, Date heureDebut, Date heureFin, Integer nombrePlaces) {
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

    // Date du concert (jour uniquement)
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    // Heure de début du concert
    public Date getHeureDebut() {
        return heureDebut;
    }

    public void setHeureDebut(Date heureDebut) {
        this.heureDebut = heureDebut;
    }

    // Heure de fin du concert
    public Date getHeureFin() {
        return heureFin;
    }

    public void setHeureFin(Date heureFin) {
        this.heureFin = heureFin;
    }

    public Integer getNombrePlaces() {
        return nombrePlaces;
    }

    public void setNombrePlaces(Integer nombrePlaces) {
        this.nombrePlaces = nombrePlaces;
    }

    // Plusieurs concerts peuvent être organisés par un même organisateur
    @ManyToOne
    @JsonBackReference // Évite la sérialisation de l'organisateur pour prévenir la récursion infinie
    public Organisateur getOrganisateur() {
        return organisateur;
    }

    public void setOrganisateur(Organisateur organisateur) {
        this.organisateur = organisateur;
    }

    // Plusieurs concerts peuvent avoir lieu dans la même salle
    @ManyToOne
    @JsonBackReference
    public Salle getSalle() {
        return salle;
    }
    

    public void setSalle(Salle salle) {
        this.salle = salle;
    }

    // Un concert peut avoir plusieurs tickets
    @OneToMany(mappedBy = "concert")
    @JsonManagedReference
    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }

    // Un concert peut accueillir plusieurs artistes
    @ManyToMany
    public List<Artiste> getArtistes() {
        return artistes;
    }

    public void setArtistes(List<Artiste> artistes) {
        this.artistes = artistes;
    }
}
