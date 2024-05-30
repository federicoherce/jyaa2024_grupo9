package entityManager;


import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;


@WebListener
public class AppContextListener implements ServletContextListener {


    public void contextInitialized(ServletContextEvent sce)  { 
         // TODO Auto-generated method stub
    }


    public void contextDestroyed(ServletContextEvent sce)  { 
        EntityManagerFactorySingleton.close();
    }
	
}
