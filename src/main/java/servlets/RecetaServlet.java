package servlets;
import java.util.List;


import bd.Receta;
import bd.Usuario;
import dao.RecetaDAO;
import dao.UsuarioDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet implementation class RecetaServlet
 */
public class RecetaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		RecetaDAO recetaDAO = new RecetaDAO();
		
		UsuarioDAO usuarioDao = new UsuarioDAO();
		Usuario usuario = usuarioDao.findById(1);
		Receta receta = new Receta("Tarta de manzana", "Mezclar los ingredientes y hornear", usuario);
		Receta otraReceta = new Receta("Milanesa", "Empanar la carne y freir", usuario);	
		recetaDAO.persist(receta);
		recetaDAO.persist(otraReceta);
		List<Receta> recetas = recetaDAO.findAll();
		for (Receta r : recetas) {
			System.out.println(r.getTitulo());
		}
	   	System.out.println("---Eliminacion-----");
	   	
	   	recetaDAO.delete(receta);
	    recetas = recetaDAO.findAll();
	   	for (Receta r : recetas) {
			System.out.println(r.getTitulo());
		}
		
	}



}
