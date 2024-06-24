package api;

import javax.persistence.PersistenceException;

import org.hibernate.PropertyValueException;
import org.hibernate.exception.ConstraintViolationException;

import bd.FamiliaProductora;
import bd.Insumo;
import bd.Receta;
import bd.Usuario;
import dao.FamiliaProductoraDAO;
import dao.InsumoDAO;
import dao.RecetaDAO;
import dao.UsuarioDAO;
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

@Path("/recetas")
public class RecetaApi {
	
	@Inject
	private RecetaDAO recetaDao;
	
	@GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getInsumoById(@PathParam("id") int id) {
    	Receta receta = recetaDao.findActiveById(id);
        if (receta == null) {
        	String mensaje= "No se encontró la receta con id: " + id;
        	return Response.status(Response.Status.NOT_FOUND).entity(mensaje).build();
        }
        return Response.ok(receta).build();
    }
	
	@POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createReceta(Receta receta) {
    	try {
        	recetaDao.persist(receta);
    	} catch (PersistenceException e) {
            Throwable cause = e.getCause();
            if (cause instanceof ConstraintViolationException) 
            	return Response.status(Response.Status.CONFLICT).entity("El nombre ya se encuentra registrado").build();	
            if (cause instanceof PropertyValueException) 
            	return Response.status(Response.Status.CONFLICT).entity("Falta completar campo/s obligatorio/s").build();
    }
    	return Response.status(Response.Status.CREATED).entity(receta).build();
   }
	

	@PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateReceta(Receta receta){
    	Receta aux = recetaDao.findActiveById(receta.getId());
    	if (aux != null) {
    		recetaDao.update(receta);
    		return Response.ok().entity(receta).build();
    	}
	    else 
	    	return Response.status(Response.Status.NOT_FOUND).entity("La receta no existe").build(); 
    }
	
	@DELETE
    @Path("/{id}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response deleteReceta(@PathParam("id") Integer id){
    	Receta aux = recetaDao.findActiveById(id);
    	if (aux != null){
    		aux.setActivo(false);
    		recetaDao.update(aux);
    		return  Response.ok().entity("Receta eliminada").build();
	    } else {
		    return Response.status(Response.Status.NOT_FOUND).entity("Receta no encontrada").build();
	  	}
	}

}
