package config;

import org.glassfish.hk2.api.JustInTimeInjectionResolver;
//import org.glassfish.hk2.api.JustInTimeServiceResolver;
import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.process.internal.RequestScoped;


public class MyApplicationBinder extends AbstractBinder {

	@Override
	protected void configure() {
		//bind(JustInTimeServiceResolver.class)
		//.to(JustInTimeInjectionResolver.class );

	}

}
