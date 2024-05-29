package dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import bd.Permiso;

public class PermisoDAO extends GenericDAOImpl<Permiso, Long> {
	 
	@PersistenceContext
	private EntityManager entityManager;

	public PermisoDAO() {
		super(Permiso.class);
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
		
	}


}

	

