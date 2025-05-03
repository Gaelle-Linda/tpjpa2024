package fr.istic.taa.jaxrs.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import fr.istic.taa.jaxrs.dto.ArtisteDto;
import fr.istic.taa.jaxrs.dto.OrganisateurDto;


@Entity
public class Organisateur extends Personne implements Serializable {
    
    private List<Concert> concerts;

    public Organisateur(){

    }

    public Organisateur(String nom, String prenom, String email) {
        super(nom, prenom, email);
    }
    
    @OneToMany(mappedBy = "organisateur")
    @JsonManagedReference
    public List<Concert> getConcerts() {
        return concerts;
    }


    public void setConcerts(List<Concert> concerts) {
        this.concerts = concerts;
    }

    public OrganisateurDto toDto(){
        
        OrganisateurDto organisateurDto = new OrganisateurDto();

        organisateurDto.setId(this.getId());
        organisateurDto.setNom(this.getNom());
        organisateurDto.setPrenom(this.getPrenom());
        organisateurDto.setEmail(this.getEmail());
        // Si la liste de concert est non null, Convertir la liste de concerts en une liste d'IDs, sinon la laisser null/liste vide
        if (this.getConcerts() != null) {
            
            organisateurDto.setConcertsIds(this.getConcerts().stream().map(Concert::getId).collect(Collectors.toList()));
        }
        // Si l'organisateur n'a pas de concerts, on peut initialiser la liste à vide ou la laisser null 
        
        return organisateurDto;
    }

}
