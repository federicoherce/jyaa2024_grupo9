package api;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import dao.UsuarioDAO;
import bd.Usuario;

@Path("/usuarios")
public class Api {
	
	
	@GET
    public String sayHello() {
        return "Hola!";
    }
	
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUsuarioById(@PathParam("id") int id) {
    	UsuarioDAO userDao = new UsuarioDAO();
    	Usuario usuario = userDao.findById(id);
        if (usuario == null) {
        	String mensaje= "No se encontró el usuario";
        	return Response.status(Response.Status.NOT_FOUND).entity(mensaje).build();
        }
        return Response.ok(usuario).build();
        
    }
}