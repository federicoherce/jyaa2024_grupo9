package config;

import org.glassfish.jersey.server.ResourceConfig;

public class AppConfig extends ResourceConfig {

	public AppConfig() {
		register(new MyApplicationBinder());
		 packages(true, "api");
	}
}
