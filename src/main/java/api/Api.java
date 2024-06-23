package api;


//import dao.IUsuarioDAO;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import dao.FamiliaProductoraDAO;
import dao.UsuarioDAO;
import bd.Usuario;

@Path("/pruebas")
public class Api {
	
	@Inject
	private UsuarioDAO userDao;
			
	//  private IUsuarioDAO userDao;
	
	@GET
    public String sayHello() {
        return "Hola!";
    }
		
	
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUsuarioById(@PathParam("id") int id) {
    	Usuario usuario = userDao.findById(id);
        if (usuario == null) {
        	String mensaje= "No se encontró el usuario";
        	return Response.status(Response.Status.NOT_FOUND).entity(mensaje).build();
        }
        return Response.ok(usuario).build();  
    }
}