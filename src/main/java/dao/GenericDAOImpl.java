package dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

public abstract class GenericDAOImpl<T, ID> implements GenericDAO<T, ID> {

    private Class<T> entityClass;

    @PersistenceContext
    protected EntityManager entityManager;

    public GenericDAOImpl(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    @Override
    @Transactional
    public void persist(T entity) {
        entityManager.persist(entity);
    }

    @Override
    public T findById(ID id) {
        return entityManager.find(entityClass, id);
    }

    @Override
    public List<T> findAll() {
        return entityManager.createQuery("from " + entityClass.getName(), entityClass).getResultList();
    }

    @Override
    @Transactional
    public void update(T entity) {
        entityManager.merge(entity);
    }

    @Override
    @Transactional
    public void delete(T entity) {
        entityManager.remove(entityManager.contains(entity) ? entity : entityManager.merge(entity));
    }
}