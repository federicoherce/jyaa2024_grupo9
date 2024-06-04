package dao;

import javax.persistence.EntityManager;

import bd.Insumo;
import entityManager.EntityManagerFactorySingleton;

public class InsumoDAO extends GenericDAOImpl<Insumo, Integer> {

	public InsumoDAO() {
		super(Insumo.class);
	}
	/*
    @Override
    public void persist(Insumo entity) {
        if (!exists(entity.getNombre())) 
            super.persist(entity); 
    }
    
    @Override
    public void update(Insumo entity) {
        if (!exists(entity.getNombre())) 
            super.update(entity); 
    }

    private boolean exists(String nombre) {
    	EntityManager em = EntityManagerFactorySingleton.getInstance().createEntityManager();
        Long count = em.createQuery("SELECT COUNT(i) FROM Insumo i WHERE i.nombre = :nombre", Long.class)
                                  .setParameter("nombre", nombre)
                                  .getSingleResult(); 
        return count > 0; 
    }
    */
}
	

