package fr.istic.taa.jaxrs.dto;

import java.io.Serializable;
import java.util.List;


import fr.istic.taa.jaxrs.domain.Concert;
import fr.istic.taa.jaxrs.domain.Personne;



public class OrganisateurDto extends Personne implements Serializable {
    
    private List<Long> concerts_ids;

    public OrganisateurDto(){

    }

    public OrganisateurDto(String nom, String prenom, String email) {
        super(nom, prenom, email);
    }
    

    public List<Long> getConcertsIds() {
        return concerts_ids;
    }

    public void setConcertsIds(List<Long> concerts) {
        this.concerts_ids = concerts;
    }
}


