package api;

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
import dao.FamiliaProductoraDAO;
import dao.UsuarioDAO;

import javax.persistence.PersistenceException;

import org.hibernate.PropertyValueException;
import org.hibernate.exception.ConstraintViolationException;

import bd.FamiliaProductora;
import bd.Usuario;

@Path("/familias_productoras")
public class FamiliaProductoraApi {

	@Inject
	private FamiliaProductoraDAO fpDao;
	
	@GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getFpById(@PathParam("id") int id) {
    	FamiliaProductora fp = fpDao.findActiveById(id);
        if (fp == null) {
        	String mensaje= "No se encontró la familia productora con id: " + id;
        	return Response.status(Response.Status.NOT_FOUND).entity(mensaje).build();
        }
        return Response.ok(fp).build();
        
    }
	
	@POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createFamiliaProductora(FamiliaProductora fp) {
    	try {
        	fpDao.persist(fp);
    	} catch (PersistenceException e) {
            Throwable cause = e.getCause();
            if (cause instanceof ConstraintViolationException) 
            	return Response.status(Response.Status.CONFLICT).entity("El nombre ya se encuentra registrado").build();	
            if (cause instanceof PropertyValueException) 
            	return Response.status(Response.Status.CONFLICT).entity("Falta completar campo/s obligatorio/s").build();
    }
    	return Response.status(Response.Status.CREATED).entity(fp).build();
   }
	
	@PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateFamiliaProductora(FamiliaProductora fp){
    	FamiliaProductora aux = fpDao.findActiveById(fp.getId());
    	if (aux != null) {
    		fpDao.update(fp);
    		return Response.ok().entity(fp).build();
    	}
	    else 
	    	return Response.status(Response.Status.NOT_FOUND).entity("La familia productora no existe").build(); 
    }
	
	@DELETE
    @Path("/{id}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response deleteFamiliaProductora(@PathParam("id") Integer id){
    	FamiliaProductora aux = fpDao.findActiveById(id);
    	if (aux != null){
    		aux.setActivo(false);
    		fpDao.update(aux);
    		return  Response.ok().entity("Familia Productora eliminada").build();
	    } else {
		    return Response.status(Response.Status.NOT_FOUND).entity("Familia Productora no encontrada").build();
	  	}
	}
	
}
