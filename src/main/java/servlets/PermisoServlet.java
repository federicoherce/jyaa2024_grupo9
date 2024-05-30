package servlets;

import jakarta.servlet.ServletException;


import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import java.util.List;

import bd.Permiso;
import dao.PermisoDAO;

/**
 * Servlet implementation class PermisoServlet
 */

public class PermisoServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;


    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	
        PermisoDAO permisoDAO = new PermisoDAO();
    	Permiso permiso = new Permiso("admin");
        Permiso otroPermiso = new Permiso("visitante");
        permisoDAO.persist(permiso);
        permisoDAO.persist(otroPermiso);
        List<Permiso> permisos = permisoDAO.findAll();
        for (Permiso p : permisos) {
        	System.out.println(p.getTitulo());
        }
        
       	System.out.println("----------------");
        
        permisoDAO.delete(otroPermiso);
        permisos = permisoDAO.findAll();
        for (Permiso p : permisos) {
        	System.out.println(p.getTitulo());
        }
        
        Permiso encontrado = permisoDAO.findById(permiso.getId());
        System.out.println("Titulo viejo " + encontrado.getTitulo());
        encontrado.setTitulo("Administrador");
        permisoDAO.update(encontrado);
        encontrado = permisoDAO.findById(1);
        System.out.println("Titulo nuevo " + encontrado.getTitulo());
        
        
    }
}

