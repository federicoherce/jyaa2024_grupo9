package dao;

import org.jvnet.hk2.annotations.Service;

import bd.Usuario;

// public class UsuarioDAO extends GenericDAOImpl<Usuario, Integer> implements IUsuarioDAO{

@Service
public class UsuarioDAO extends GenericDAOImpl<Usuario, Integer>{

	
	public UsuarioDAO() {
		super(Usuario.class);
	}



}
