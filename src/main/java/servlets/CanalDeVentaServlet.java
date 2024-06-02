package servlets;

import jakarta.servlet.ServletException;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import bd.CanalDeVenta;
import dao.CanalDeVentaDAO;



public class CanalDeVentaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CanalDeVentaDAO canalDAO = new CanalDeVentaDAO(); 
		CanalDeVenta canal = new CanalDeVenta("Primer Canal", "La Plata");
		CanalDeVenta otroCanal = new CanalDeVenta("Segundo Canal", "Buenos Aires");
		
		canalDAO.persist(canal);
		canalDAO.persist(otroCanal);
		List<CanalDeVenta> canales = canalDAO.findAll();
		for (CanalDeVenta cdv : canales) {
			System.out.println(cdv.getNombre());
		}
		
       	System.out.println("---Eliminacion-----");
        
       	canalDAO.delete(otroCanal);
       	canales = canalDAO.findAll();
		for (CanalDeVenta cdv : canales) {
			System.out.println(cdv.getNombre());
		}
       	
		
        System.out.println("Nombre de canal viejo " + canal.getNombre());
        canal.setNombre("La Justa");
        canalDAO.update(canal);
        CanalDeVenta encontrado = canalDAO.findById(1);
        System.out.println("Nombre de canal nuevo " + encontrado.getNombre());

	}


}
