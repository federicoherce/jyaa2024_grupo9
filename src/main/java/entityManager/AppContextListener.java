package entityManager;


import bd.CanalDeVenta;
import dao.CanalDeVentaDAO;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;


@WebListener
public class AppContextListener implements ServletContextListener {


    public void contextInitialized(ServletContextEvent sce)  { 

    }


    public void contextDestroyed(ServletContextEvent sce)  { 
        EntityManagerFactorySingleton.close();
    }
	
}
