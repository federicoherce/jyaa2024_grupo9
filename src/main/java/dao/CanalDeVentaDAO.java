package dao;

import javax.persistence.EntityManager;

import bd.CanalDeVenta;
import bd.Insumo;
import entityManager.EntityManagerFactorySingleton;

public class CanalDeVentaDAO extends GenericDAOImpl<CanalDeVenta, Integer> {


	public CanalDeVentaDAO() {
		super(CanalDeVenta.class);
	}
	
    @Override
    public void persist(CanalDeVenta entity) {
        if (!exists(entity.getNombre())) 
            super.persist(entity); 
    }
    
    @Override
    public void update(CanalDeVenta entity) {
        if (!exists(entity.getNombre())) 
            super.update(entity); 
    }

    private boolean exists(String nombre) {
    	EntityManager em = EntityManagerFactorySingleton.getInstance().createEntityManager();
        Long count = em.createQuery("SELECT COUNT(i) FROM CanalDeVenta i WHERE i.nombre = :nombre", Long.class)
                                  .setParameter("nombre", nombre)
                                  .getSingleResult(); 
        return count > 0; 
    }

}
