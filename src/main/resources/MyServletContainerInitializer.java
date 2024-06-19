import java.util.Set;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;

public class MyServletContainerInitializer implements jakarta.servlet.ServletContainerInitializer {

	private static final Logger LOGGER = LoggerFactory.getLogger(MyServletContainerInitializer.class);
	
	 public MyServletContainerInitializer(){
		 super();
	 }
	 
	 @Override
	 public void onStartup(final Set<Class<?>> handlerTypeSet, final ServletContext servletContext) throws ServletException {
		 servletContext.addServlet(SERVLET_NAME, MyServlet.class);
		 servletContext.addFilter(FILTER_NAME, MyFilter.class);
		 servletContext.addListener(MyListener.class);
	 }


}
