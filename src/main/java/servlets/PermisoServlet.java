package servlets;

import jakarta.servlet.ServletException;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;

import bd.Permiso;
import dao.PermisoDAO;

/**
 * Servlet implementation class PermisoServlet
 */

public class PermisoServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;
    private PermisoDAO permisoDAO;

    @Override
    public void init() throws ServletException {
        entityManagerFactory = Persistence.createEntityManagerFactory("miUP"); 
        entityManager = entityManagerFactory.createEntityManager();
        permisoDAO = new PermisoDAO(entityManager);
    }

    @Override
    public void destroy() {
        entityManager.close();
        entityManagerFactory.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        Permiso permiso = new Permiso("otro");
        permisoDAO.persist(permiso);
        List<Permiso> permisos = permisoDAO.findAll();
        for (Permiso p : permisos) {
        	System.out.println(p.getTitulo());
        }
        permisoDAO.delete(permiso);
        permisos = permisoDAO.findAll();
        for (Permiso p : permisos) {
        	System.out.println(p.getTitulo());
        }
        
    }
}

