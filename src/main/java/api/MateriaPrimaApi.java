package api;

import jakarta.inject.Inject;
import bd.MateriaPrima;
import jakarta.validation.ConstraintViolationException;
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
import dao.FamiliaProductoraDAO;
import dao.MateriaPrimaDAO;

import java.util.List;

import javax.persistence.PersistenceException;
import javax.persistence.RollbackException;

import org.hibernate.PropertyValueException;

import bd.FamiliaProductora;

@Path("/materiasPrimas")
public class MateriaPrimaApi {

	@Inject
	MateriaPrimaDAO materiaPrimaDAO;
	@Inject
	FamiliaProductoraDAO familiaProductoraDAO;

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response crearMateriaPrima(MateriaPrima materiaPrima) {
		FamiliaProductora productor = materiaPrima.getProductor();
		if (productor == null || productor.getNombre() == null || productor.getNombre().isEmpty()) {
			return Response.status(Status.BAD_REQUEST).entity("El nombre del productor es requerido").build();
		}
		try {
			FamiliaProductora productorEncontrado = familiaProductoraDAO.getByName(productor.getNombre());
			if (productorEncontrado == null) {
				return Response.status(Status.NOT_FOUND).entity("El productor especificado no existe").build();
			}
			materiaPrima.setProductor(productorEncontrado);
			materiaPrimaDAO.persist(materiaPrima);
			return Response.status(Status.CREATED).entity("Materia prima creada exitosamente").build();
	   	} catch (PersistenceException e) {
	        Throwable cause = e.getCause();
	        if (cause instanceof ConstraintViolationException) 
	        	return Response.status(Response.Status.CONFLICT).entity("El nombre ya se encuentra registrado").build();	
	        if (cause instanceof PropertyValueException) 
	        	return Response.status(Response.Status.CONFLICT).entity("Falta completar campo/s obligatorio/s").build();
	}
	return Response.status(Status.INTERNAL_SERVER_ERROR).entity("Error interno del servidor").build();
}
		

	

// baja logica de materia prima
	@DELETE
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteMateriaPrima(@PathParam("id") int id) {
		MateriaPrima materiaPrima = materiaPrimaDAO.findById(id);
		if (materiaPrima == null) {
			String mensaje = "No se encontró la materia prima";
			return Response.status(Response.Status.NOT_FOUND).entity(mensaje).build();
		}
		materiaPrima.setActivo(false);
		materiaPrimaDAO.update(materiaPrima);
		return Response.ok(materiaPrima).build();
	}






// Editar materia prima
@PUT
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public Response updateMateriaPrima(MateriaPrima materiaPrima) {
	MateriaPrima aux = materiaPrimaDAO.findById(materiaPrima.getId());
	if (aux != null) {
		materiaPrimaDAO.update(materiaPrima);
		return Response.ok().entity(materiaPrima).build();
	} else
		return Response.status(Response.Status.NOT_FOUND).entity("La materia prima no existe").build();
}




}








