package models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.util.List;

@Entity
public class Salle {
    private Long id;
    private String description;
    private Integer nombrePlacesMax;
    private List<Concert> concerts;

    public Salle() {}

    public Salle(String description, Integer nombrePlacesMax) {
        this.description = description;
        this.nombrePlacesMax = nombrePlacesMax;
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

    public Integer getNombrePlacesMax() {
        return nombrePlacesMax;
    }

    public void setNombrePlacesMax(Integer nombrePlacesMax) {
        this.nombrePlacesMax = nombrePlacesMax;
    }

    @OneToMany(mappedBy = "salle")
    public List<Concert> getConcerts() {
        return concerts;
    }

    public void setConcerts(List<Concert> concerts) {
        this.concerts = concerts;
    }
}
