package api;

import javax.persistence.PersistenceException;

import org.hibernate.PropertyValueException;
import org.hibernate.exception.ConstraintViolationException;

import bd.Usuario;
import dao.UsuarioDAO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

@Path("/users")
public class Usuarios {
	
	@Inject
	private UsuarioDAO userDao;
	
	@GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Obtener a un usuario por su ID")
	public Response getUsuarioById(@PathParam("id") int id) {
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
	public Response createUser(@RequestBody Usuario usuario) {
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
    public Response updateUser(Usuario usuario){
    	Usuario aux = userDao.findActiveById(usuario.getId());
    	if (aux != null) {
    		userDao.update(usuario);
    		return Response.ok().entity(usuario).build();
    	}
	    else 
	    	return Response.status(Response.Status.NOT_FOUND).entity("El usuario no existe").build(); 
    }
    
    @DELETE
    @Path("/{id}")
    @Produces(MediaType.TEXT_PLAIN)
    @Operation(summary = "Eliminacion de un usuario por su ID")
    public Response deleteUser(@PathParam("id") Integer id){
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
