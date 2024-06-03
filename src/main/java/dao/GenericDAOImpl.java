package dao;


import javax.persistence.EntityManager;
import entityManager.EntityManagerFactorySingleton;

import java.util.List;

import javax.persistence.EntityManager;

import entityManager.EntityManagerFactorySingleton;

public abstract class GenericDAOImpl<T, ID> implements GenericDAO<T, ID> {

	private Class<T> entityClass;


	public GenericDAOImpl(Class<T> entityClass) {
		this.entityClass = entityClass;
	}

    @Override
    public void persist(T entity) {
        EntityManager em = EntityManagerFactorySingleton.getInstance().createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(entity);
            em.getTransaction().commit();
        } catch (RuntimeException e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw e;
        } finally {
            em.close();
        }
    }


    @Override
    public T findById(ID id) {
    	EntityManager em = EntityManagerFactorySingleton.getInstance().createEntityManager();
    	try {
            return em.find(entityClass, id);
        } finally {
            em.close();
        }
    }

    @Override
    public List<T> findAll() {
    	EntityManager em = EntityManagerFactorySingleton.getInstance().createEntityManager();
    	try {
            return em.createQuery("from " + entityClass.getName(), entityClass).getResultList();
        } finally {
            em.close();
        }
    }


    @Override
    public void update(T entity) {
    	EntityManager em = EntityManagerFactorySingleton.getInstance().createEntityManager();
    	try {
            em.getTransaction().begin();
            em.merge(entity);
            em.getTransaction().commit();
        } catch (RuntimeException e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw e;
        } finally {
            em.close();
        }
    }

    @Override
    public void delete(T entity) {
    	EntityManager em = EntityManagerFactorySingleton.getInstance().createEntityManager();
    	try {
            em.getTransaction().begin();
            em.remove(em.contains(entity) ? entity : em.merge(entity));
            em.getTransaction().commit();
        } catch (RuntimeException e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw e;
        } finally {
            em.close();
        }
    }
    

    
}