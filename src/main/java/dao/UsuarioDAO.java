package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import org.jvnet.hk2.annotations.Service;

import bd.Usuario;
import entityManager.EntityManagerFactorySingleton;
import jakarta.inject.Inject;

// public class UsuarioDAO extends GenericDAOImpl<Usuario, Integer> implements IUsuarioDAO{

@Service
public class UsuarioDAO extends GenericDAOImpl<Usuario, Integer>{

	public UsuarioDAO() {
		super(Usuario.class);
	}
	
    
    public Usuario findActiveByEmail(String email) {
    	EntityManager em = EntityManagerFactorySingleton.getInstance().createEntityManager();
    	try {
    		return em.createQuery(
    	            "SELECT e FROM Usuario e WHERE e.email = :email AND e.activo = true", Usuario.class)
    	            .setParameter("email", email).getSingleResult();
        } catch (NoResultException e) {
            return null;
        } finally {
            em.close();
        }
    }



}
