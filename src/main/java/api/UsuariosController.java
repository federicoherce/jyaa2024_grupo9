package api;

import javax.persistence.PersistenceException;
import org.hibernate.PropertyValueException;
import org.hibernate.exception.ConstraintViolationException;
import bd.Usuario;
import dao.UsuarioDAO;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.servers.Server;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import requests.UsuarioRequest;


@OpenAPIDefinition(
	    info = @Info(title = "API", version = "1.0.0"),
	    servers = @Server(url = "http://localhost:8080/Sala/")
	)


@Path("/users")
public class UsuariosController {
	
	@Inject
	private UsuarioDAO userDao;
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Operation(summary = "Obtener un usuario por su ID")
	@ApiResponses(value = {
	    @ApiResponse(responseCode = "200", description = "Usuario encontrado",
	        content = @Content(mediaType = "application/json",
	        schema = @Schema(implementation = Usuario.class))),
	    @ApiResponse(responseCode = "404", description = "Usuario no encontrado")
	})
	public Response getUsuarioById(@Parameter(description = "ID del usuario", required = true) @PathParam("id") int id) {
    	Usuario usuario = userDao.findActiveById(id);
        if (usuario == null) {
        	String mensaje= "No se encontró el usuario";
        	return Response.status(Response.Status.NOT_FOUND).entity(mensaje).build();
        }
        return Response.ok(usuario).build();
    }
	
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Operation(summary = "Creacion de un nuevo usuario")
	@ApiResponses(value = {
	    @ApiResponse(responseCode = "201", description = "Usuario creado",
	        content = @Content(mediaType = "application/json",
	        schema = @Schema(implementation = Usuario.class))),
	    @ApiResponse(responseCode = "409", description = "Conflicto de datos")
	})
	public Response createUser(@Parameter(description = "Datos del nuevo usuario", required = true) UsuarioRequest usuarioRequest) {
	    Usuario usuario = new Usuario(usuarioRequest.getEmail(), usuarioRequest.getNombre(),
	    		usuarioRequest.getApellido(), usuarioRequest.getPassword());
		try {
        	userDao.persist(usuario);
    	} catch (PersistenceException e) {
            Throwable cause = e.getCause();
            if (cause instanceof ConstraintViolationException) 
            	return Response.status(Response.Status.CONFLICT).entity("El email ya se encuentra registrado").build();	
            if (cause instanceof PropertyValueException) 
            	return Response.status(Response.Status.CONFLICT).entity("Falta completar campo/s obligatorio/s").build();
    }
    	return Response.status(Response.Status.CREATED).entity(usuario).build();
   }
    
	
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Operation(summary = "Actualización de un usuario")
	@ApiResponses(value = {
	    @ApiResponse(responseCode = "200", description = "Usuario actualizado",
	        content = @Content(mediaType = "application/json",
	        schema = @Schema(implementation = Usuario.class))),
	    @ApiResponse(responseCode = "404", description = "Usuario no encontrado")
	})
	public Response updateUser(@Parameter(description = "Datos del usuario a actualizar", required = true) UsuarioRequest usuarioRequest) {
	    Usuario aux = userDao.findActiveByEmail(usuarioRequest.getEmail());
	    if (aux != null) {
	        aux.setEmail(usuarioRequest.getEmail());
	        aux.setNombre(usuarioRequest.getNombre());
	        aux.setApellido(usuarioRequest.getApellido());
	        aux.setPassword(usuarioRequest.getPassword());
	        userDao.update(aux);
	        return Response.ok().entity(aux).build();
	    } else {
	        return Response.status(Response.Status.NOT_FOUND).entity("El usuario no existe").build();
	    }
	}
    
	
	@DELETE
	@Path("/{id}")
	@Produces(MediaType.TEXT_PLAIN)
	@Operation(summary = "Eliminar un usuario por su ID")
	@ApiResponses(value = {
	    @ApiResponse(responseCode = "200", description = "Usuario eliminado"),
	    @ApiResponse(responseCode = "404", description = "Usuario no encontrado")
	})
	public Response deleteUser(@Parameter(description = "ID del usuario", required = true) @PathParam("id") Integer id) {
    	Usuario aux = userDao.findActiveById(id);
    	if (aux != null){
    		aux.setActivo(false);
    		userDao.update(aux);
    		return  Response.ok().entity("Usuario eliminado").build();
	    } else {
		    return Response.status(Response.Status.NOT_FOUND).entity("Usuario no encontrado").build();
	  	}
	}
}
