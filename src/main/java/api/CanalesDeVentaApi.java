package api;

import javax.persistence.PersistenceException;

import org.hibernate.PropertyValueException;
import org.hibernate.exception.ConstraintViolationException;
import bd.CanalDeVenta;
import dao.CanalDeVentaDAO;
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
import jakarta.ws.rs.core.Response.Status;

@Path("/canalesVenta")
public class CanalesDeVentaApi {
	

	
@Inject
private CanalDeVentaDAO canalesDeVentaDao;
	


@GET
@Path("/{id}")
@Produces(MediaType.APPLICATION_JSON)
public Response getCanalDeVentaById(@PathParam("id") int id) {
	CanalDeVenta canalDeVenta = canalesDeVentaDao.findActiveById(id);
	if (canalDeVenta == null) {
		String mensaje = "No se encontró el canal de venta";
		return Response.status(Response.Status.NOT_FOUND).entity(mensaje).build();
	}
	return Response.ok(canalDeVenta).build();
}



@POST
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public Response createCanalDeVenta(CanalDeVenta canalDeVenta) {
	try {
		canalesDeVentaDao.persist(canalDeVenta);
   	} catch (PersistenceException e) {
        Throwable cause = e.getCause();
        if (cause instanceof ConstraintViolationException) 
        	return Response.status(Response.Status.CONFLICT).entity("El nombre ya se encuentra registrado").build();	
        if (cause instanceof PropertyValueException) 
        	return Response.status(Response.Status.CONFLICT).entity("Falta completar campo/s obligatorio/s").build();
}
	return Response.status(Response.Status.CREATED).entity(canalDeVenta).build();
}



@PUT
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public Response updateCanalDeVenta(CanalDeVenta canalDeVenta){
    CanalDeVenta aux = canalesDeVentaDao.findActiveById(canalDeVenta.getId());
    if (aux != null) {
        canalesDeVentaDao.update(canalDeVenta);
        return Response.ok().entity(canalDeVenta).build();
    }
    else {
        return Response.status(Status.NOT_FOUND).entity("No se encontró el canal de venta con id: " + canalDeVenta.getId()).build();
    }
}


@DELETE
@Path("/{id}")
@Produces(MediaType.TEXT_PLAIN)
public Response deleteCanalDeVenta(@PathParam("id") Integer id) {
	CanalDeVenta aux = canalesDeVentaDao.findActiveById(id);
	if (aux != null) {
		aux.setActivo(false);
		canalesDeVentaDao.update(aux);
		return Response.ok().entity("Canal de venta eliminado").build();
	} else {
		return Response.status(Response.Status.NOT_FOUND).entity("Canal de venta no encontrado").build();
	}
}














}




















