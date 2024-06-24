package api;

import jakarta.inject.Inject;
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

import javax.persistence.RollbackException;

import org.hibernate.PropertyValueException;

import bd.FamiliaProductora;

@Path("/materiasPrimas")
public class MateriaPrima {

	@Inject
	MateriaPrimaDAO materiaPrimaDAO;
	@Inject
	FamiliaProductoraDAO familiaProductoraDAO;

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response crearMateriaPrima(bd.MateriaPrima materiaPrima) {
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
		} catch (ConstraintViolationException e) {
			return Response.status(Status.CONFLICT).entity("Error: llave duplicada o violación de restricciones")
					.build();
		} catch (PropertyValueException e) {
			return Response.status(Status.BAD_REQUEST).entity("Faltan datos para crear la materia prima").build();
		} catch (Exception e) {
			return Response.status(Status.INTERNAL_SERVER_ERROR)
					.entity("Ocurrió un error inesperado al crear la materia prima").build();
		}
	}

// baja logica de materia prima
	@DELETE
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteMateriaPrima(@PathParam("id") int id) {
		bd.MateriaPrima materiaPrima = materiaPrimaDAO.findById(id);
		if (materiaPrima == null) {
			String mensaje = "No se encontró la materia prima";
			return Response.status(Response.Status.NOT_FOUND).entity(mensaje).build();
		}
		materiaPrima.setActivo(false);
		materiaPrimaDAO.update(materiaPrima);
		return Response.ok(materiaPrima).build();
	}

}
