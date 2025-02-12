package models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import java.util.List;

@Entity
public class Artiste {
    private Long id;
    private String nomArtistique;
    private String genreMusical;
    private List<Concert> concerts;

    public Artiste() {}

    public Artiste(String nomArtistique, String genreMusical) {
        this.nomArtistique = nomArtistique;
        this.genreMusical = genreMusical;
    }

    @Id
    @GeneratedValue
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomArtistique() {
        return nomArtistique;
    }

    public void setNomArtistique(String nomArtistique) {
        this.nomArtistique = nomArtistique;
    }

    public String getGenreMusical() {
        return genreMusical;
    }

    public void setGenreMusical(String genreMusical) {
        this.genreMusical = genreMusical;
    }

    @ManyToMany(mappedBy = "artistes")
    public List<Concert> getConcerts() {
        return concerts;
    }

    public void setConcerts(List<Concert> concerts) {
        this.concerts = concerts;
    }
}
