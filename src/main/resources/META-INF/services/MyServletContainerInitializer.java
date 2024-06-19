
import java.util.Set;
import api.Api;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;

public class MyServletContainerInitializer implements ServletContainerInitializer {

	private static final Logger LOGGER = LoggerFactory.getLogger(MyServletContainerInitializer.class);
	
	 public MyServletContainerInitializer(){
		 super();
	 }
	 
	 @Override
	 public void onStartup(final Set<Class<?>> handlerTypeSet, final ServletContext ctx) throws ServletException {
	     ctx.addServlet("Api", Api.class);
	 }


}

