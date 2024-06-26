package config;

import org.glassfish.jersey.server.ResourceConfig;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import jakarta.ws.rs.ApplicationPath;

@OpenAPIDefinition(
	    info = @Info(title = "API de Usuarios", version = "1.0.0"),
	    servers = @Server(url = "http://localhost:8080/Sala/")
	)

@ApplicationPath("/")
public class JerseyApplication extends ResourceConfig {

	public JerseyApplication() {
		register(new MyApplicationBinder());
		packages(true, "api");
	}

}