package config;

import org.glassfish.jersey.server.ResourceConfig;

import jakarta.ws.rs.ApplicationPath;

@ApplicationPath("/")
public class JerseyApplication extends ResourceConfig {

	public JerseyApplication() {
		register(new MyApplicationBinder());
		packages(true, "api");
	}

}