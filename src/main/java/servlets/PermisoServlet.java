package servlets;

import jakarta.servlet.ServletException;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import bd.Permiso;
import dao.PermisoDAO;

/**
 * Servlet implementation class PermisoServlet
 */
public class PermisoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    @PersistenceContext
    private EntityManager entityManager;
    
    private PermisoDAO permisoDAO;

    @Override
    public void init() throws ServletException {
        permisoDAO = new PermisoDAO();
        permisoDAO.setEntityManager(entityManager);

    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        Permiso permiso = new Permiso("admin");
        permisoDAO.persist(permiso);
	}



}
