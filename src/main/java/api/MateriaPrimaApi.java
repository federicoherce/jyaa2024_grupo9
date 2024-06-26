package api;

import jakarta.inject.Inject;


import bd.MateriaPrima;
import bd.Usuario;
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
import requests.MateriaPrimaRequest;
import dao.FamiliaProductoraDAO;
import dao.MateriaPrimaDAO;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.servers.Server;
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
	@Operation(summary = "Crear una materia prima")
	@ApiResponses(value = {
		    @ApiResponse(responseCode = "200", description = "Materia prima encontrado",
		        content = @Content(mediaType = "application/json",
		        schema = @Schema(implementation = MateriaPrima.class))),
		    @ApiResponse(responseCode = "404", description = "Materia prima no encontrado")
		})
	public Response crearMateriaPrima(MateriaPrimaRequest materiaPrima) {
		
		
		
		FamiliaProductora productor = familiaProductoraDAO.getByName(materiaPrima.getNombreProductor());
		
		MateriaPrima materia = new MateriaPrima(materiaPrima.getNombre(), materiaPrima.getPeso()
				,materiaPrima.getFechaCompra(),materiaPrima.getFechaVencimiento(), materiaPrima.getCostoPorKg(), 
				materiaPrima.getFormaAlmacenamiento(), productor);
		
		
		//FamiliaProductora productor = materiaPrima.getProductor();
		if (productor == null || productor.getNombre() == null || productor.getNombre().isEmpty()) {
			return Response.status(Status.BAD_REQUEST).entity("El nombre del productor es requerido").build();
		}
		try {
			FamiliaProductora productorEncontrado = familiaProductoraDAO.getByName(productor.getNombre());
			if (productorEncontrado == null) {
				return Response.status(Status.NOT_FOUND).entity("El productor especificado no existe").build();
			}
			materia.setProductor(productorEncontrado);
			materiaPrimaDAO.persist(materia);
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
	@Operation(summary = "Eliminar una materia prima")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Materia prima eliminada"),
			@ApiResponse(responseCode = "404", description = "Materia prima no encontrada") })
	public Response deleteMateriaPrima(@Parameter(description = "ID de MateriaPrima", required = true)   @PathParam("id") int id) {
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
@Operation(summary = "Actualizar una materia prima")
@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "Materia prima actualizada", content = @Content(mediaType = "application/json", schema = @Schema(implementation = MateriaPrima.class))),
		@ApiResponse(responseCode = "404", description = "Materia prima no encontrada") })
public Response updateMateriaPrima(MateriaPrimaRequest materiaPrima) {
	MateriaPrima aux = materiaPrimaDAO.findById(materiaPrima.getId());
	if (aux != null) {
		
		aux.setNombre(materiaPrima.getNombre());
		aux.setCostoPorKg(materiaPrima.getCostoPorKg());
		aux.setPeso(materiaPrima.getPeso());
		aux.setCostoPorKg(materiaPrima.getCostoPorKg());
		aux.setFormaAlmacenamiento(materiaPrima.getFormaAlmacenamiento());
		aux.setFechaCompra(materiaPrima.getFechaCompra());
		aux.setFechaVencimiento(materiaPrima.getFechaVencimiento());
		
			
		
		materiaPrimaDAO.update(aux);
		
		return Response.ok().entity(aux).build();
	} else
		return Response.status(Response.Status.NOT_FOUND).entity("La materia prima no existe").build();
}




}








