package dao;

import java.util.List;


public interface GenericDAO<T, ID> {
	void persist(T entity);

	T findById(ID id);

	List<T> findAll();

	void update(T entity);

	void delete(T entity);
	
	T getByName(String name);
	

}
