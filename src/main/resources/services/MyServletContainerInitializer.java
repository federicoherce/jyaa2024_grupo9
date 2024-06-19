import java.util.Set;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;

public class MyServletContainerInitializer implements jakarta.servlet.ServletContainerInitializer {

	private static final Logger LOGGER = LoggerFactory.getLogger(MyServletContainerInitializer.class);
	
	 public MyServletContainerInitializer(){
		 super();
	 }
	 
	 @Override
	 public void onStartup(final Set<Class<?>> handlerTypeSet, final ServletContext ctx) throws ServletException {
		// Crear una instancia de ResourceConfig para configurar Jersey.
	     ResourceConfig resourceConfig = new ResourceConfig();

	     // Registrar paquetes que contienen los recursos y proveedores de Jersey.
	     resourceConfig.packages("api");

	     // Registrar el servlet de Jersey.
	      ctx.addServlet("Api", new ServletContainer(resourceConfig)).addMapping("/*");
	 }


}
