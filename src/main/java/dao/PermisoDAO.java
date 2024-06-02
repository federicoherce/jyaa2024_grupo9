package dao;


import javax.persistence.EntityManager;
import bd.Permiso;
import entityManager.EntityManagerFactorySingleton;

public class PermisoDAO extends GenericDAOImpl<Permiso, Integer> {



	public PermisoDAO() {
		super(Permiso.class);
	}

    @Override
    public void persist(Permiso entity) {
        if (!exists(entity.getTitulo())) 
            super.persist(entity); 
    }
    
    @Override
    public void update(Permiso entity) {
        if (!exists(entity.getTitulo())) 
            super.update(entity); 
    }

    private boolean exists(String titulo) {
    	EntityManager em = EntityManagerFactorySingleton.getInstance().createEntityManager();
        Long count = em.createQuery("SELECT COUNT(i) FROM Permiso i WHERE i.titulo = :titulo", Long.class)
                                  .setParameter("titulo", titulo)
                                  .getSingleResult(); 
        return count > 0; 
    }


}
