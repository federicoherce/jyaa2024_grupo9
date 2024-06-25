package api;

import java.util.List;

import javax.persistence.PersistenceException;

import org.hibernate.PropertyValueException;
import org.hibernate.exception.ConstraintViolationException;
import bd.FamiliaProductora;
import bd.Insumo;
import bd.Usuario;
import dao.FamiliaProductoraDAO;
import dao.InsumoDAO;
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

@Path("/insumos")
public class InsumosApi {
	
	@Inject
	private InsumoDAO insumoDao;
	
	@GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getInsumoById(@PathParam("id") int id) {
    	Insumo insumo = insumoDao.findActiveById(id);
        if (insumo == null) {
        	String mensaje= "No se encontró el insumo con id: " + id;
        	return Response.status(Response.Status.NOT_FOUND).entity(mensaje).build();
        }
        return Response.ok(insumo).build();
    }
	
	@GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllInsumos(@PathParam("id") int id) {
    	List<Insumo> insumos = insumoDao.findAll();
        if (insumos == null) {
        	String mensaje= "No se encontraron insumos";
        	return Response.status(Response.Status.NOT_FOUND).entity(mensaje).build();
        }
        return Response.ok(insumos).build();
    }
	
	
	@POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createInsumo(Insumo insumo) {
    	try {
        	insumoDao.persist(insumo);
    	} catch (PersistenceException e) {
            Throwable cause = e.getCause();
            if (cause instanceof ConstraintViolationException) 
            	return Response.status(Response.Status.CONFLICT).entity("El nombre ya se encuentra registrado").build();	
            if (cause instanceof PropertyValueException) 
            	return Response.status(Response.Status.CONFLICT).entity("Falta completar campo/s obligatorio/s").build();
    }
    	return Response.status(Response.Status.CREATED).entity(insumo).build();
   }
	

	@PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateInsumo(Insumo insumo){
    	Insumo aux = insumoDao.findActiveById(insumo.getId());
    	if (aux != null) {
    		insumoDao.update(insumo);
    		return Response.ok().entity(insumo).build();
    	}
	    else 
	    	return Response.status(Response.Status.NOT_FOUND).entity("El insumo no existe").build(); 
    }
	
	@DELETE
    @Path("/{id}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response deleteInsumo(@PathParam("id") Integer id){
    	Insumo aux = insumoDao.findActiveById(id);
    	if (aux != null){
    		aux.setActivo(false);
    		insumoDao.update(aux);
    		return  Response.ok().entity("Insumo eliminado").build();
	    } else {
		    return Response.status(Response.Status.NOT_FOUND).entity("Insumo no encontrado").build();
	  	}
	}

}
