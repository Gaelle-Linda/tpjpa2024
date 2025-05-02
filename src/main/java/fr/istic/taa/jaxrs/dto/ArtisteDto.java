package fr.istic.taa.jaxrs.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

public class ArtisteDto implements Serializable {
    private Long id;
    private String nomArtistique;
    private String genreMusical;
    private List<Long> concerts_ids;
    
    public ArtisteDto() {}

    public ArtisteDto(String nomArtistique, String genreMusical) {
        this.nomArtistique = nomArtistique;
        this.genreMusical = genreMusical;
    }

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

    public List<Long> getConcertsIds() {
        return concerts_ids;
    }

    public void setConcertsIds(List<Long> concerts) {
        this.concerts_ids = concerts;
    }


}
