package fr.istic.taa.jaxrs.dao;

import fr.istic.taa.jaxrs.dao.generic.AbstractJpaDao;
import fr.istic.taa.jaxrs.domain.Salle;

public class SalleDao extends AbstractJpaDao<Long, Salle> {
    public SalleDao(){
        super();
        this.setClazz(Salle.class);
    }

}
