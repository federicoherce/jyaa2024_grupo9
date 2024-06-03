package servlets;

import jakarta.servlet.ServletException;
import bd.Usuario;
import dao.UsuarioDAO;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;



/**
 * Servlet implementation class UsuarioServlet
 */
public class UsuarioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UsuarioServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	//Creo un usuario de prueba
		UsuarioDAO usuarioDAO = new UsuarioDAO();
    	Usuario user = new Usuario("fede@gmail.com", "Fede", "Lopez", "1234");
    	usuarioDAO.persist(user);
    	List<Usuario> usuarios = usuarioDAO.findAll();
		for (Usuario u : usuarios) {
			System.out.println(u.getNombre());
		}
		//actualizo el usuario
		System.out.println("---Actualizacion-----");
		user.setNombre("Federico");
		usuarioDAO.update(user);
		System.out.println(user.getNombre());
		
		
		
		System.out.println("---Eliminacion-----");
		usuarioDAO.delete(user);
		usuarios = usuarioDAO.findAll();
		for (Usuario u : usuarios) {
			System.out.println(u.getNombre());
		}
    	
    	
    	
	}


}
