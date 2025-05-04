package fr.istic.taa.jaxrs.dao;

import fr.istic.taa.jaxrs.dao.generic.AbstractJpaDao;
import fr.istic.taa.jaxrs.domain.Personne;

public class PersonneDao extends AbstractJpaDao<Long, Personne> {
    public PersonneDao(){
        super();
        this.setClazz(Personne.class);
    }

}
