package fr.istic.taa.jaxrs.dao;

import fr.istic.taa.jaxrs.dao.generic.AbstractJpaDao;
import fr.istic.taa.jaxrs.domain.Artiste;
import fr.istic.taa.jaxrs.domain.Client;

public class ArtisteDao extends AbstractJpaDao<Long, Artiste> {
    public ArtisteDao(){
        super();
        this.setClazz(Artiste.class);
    }

}
