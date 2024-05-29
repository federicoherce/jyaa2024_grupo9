package dao;

import javax.persistence.EntityManager;

import bd.Permiso;

public class PermisoDAO extends GenericDAOImpl<Permiso, Integer> {



	public PermisoDAO(EntityManager entityManager) {
		super(Permiso.class, entityManager);
	}



}
