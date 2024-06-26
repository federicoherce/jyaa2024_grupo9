package api;

import requests.CanalDeVentaRequest;
import javax.persistence.PersistenceException;

import org.hibernate.PropertyValueException;
import org.hibernate.exception.ConstraintViolationException;
import bd.CanalDeVenta;
import bd.Usuario;
import dao.CanalDeVentaDAO;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.servers.Server;
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
@Operation(summary = "Obtener un canal de venta por su ID")
@ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = "Canal de venta encontrado",
        content = @Content(mediaType = "application/json",
        schema = @Schema(implementation = CanalDeVenta.class))),
    @ApiResponse(responseCode = "404", description = "Canal de venta no encontrado")
})
public Response getCanalDeVentaById(@Parameter(description = "ID del canal de ventas", required = true)@PathParam("id") int id) {
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
@Operation(summary = "Crear un canal de venta ")
@ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = "Canal de venta creado correctamente",
        content = @Content(mediaType = "application/json",
        schema = @Schema(implementation = CanalDeVenta.class))),
    @ApiResponse(responseCode = "409", description = "Conflicto de datos")
})
public Response createCanalDeVenta(CanalDeVentaRequest canalDeVenta) {
	CanalDeVenta canal = new CanalDeVenta(canalDeVenta.getNombre(), canalDeVenta.getUbicacion());
	
	try {
		canalesDeVentaDao.persist(canal);
   	} catch (PersistenceException e) {
        Throwable cause = e.getCause();
        if (cause instanceof ConstraintViolationException) 
        	return Response.status(Response.Status.CONFLICT).entity("El nombre ya se encuentra registrado").build();	
        if (cause instanceof PropertyValueException) 
        	return Response.status(Response.Status.CONFLICT).entity("Falta completar campo/s obligatorio/s").build();
}
	return Response.status(Response.Status.CREATED).entity(canal).build();
}



@PUT
@Path("/{id}")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Operation(summary = "Actualizar un canal de venta ")
@ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = "Canal de venta actualizado correctamente",
        content = @Content(mediaType = "application/json",
        schema = @Schema(implementation = CanalDeVenta.class))),
    @ApiResponse(responseCode = "409", description = "Conflicto de datos")
})
public Response updateCanalDeVenta(@Parameter(description = "ID del canal de ventas", required = true)@PathParam("id") Integer id,CanalDeVentaRequest canalDeVenta){
    CanalDeVenta aux = canalesDeVentaDao.findActiveById(id);
    if (aux != null) {
    	aux.setNombre(canalDeVenta.getNombre());
        aux.setUbicacion(canalDeVenta.getUbicacion());
        canalesDeVentaDao.update(aux);
        return Response.ok().entity(aux).build();
    }
    else {
        return Response.status(Status.NOT_FOUND).entity("No se encontró el canal de venta con id: " + id).build();
    }
}


@DELETE
@Path("/{id}")
@Produces(MediaType.TEXT_PLAIN)
@Operation(summary = "Eliminar un canal de venta")
@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Canal de venta eliminado"),
		@ApiResponse(responseCode = "404", description = "Canal de venta no encontrado") })

public Response deleteCanalDeVenta(@Parameter(description = "ID del canal de ventas", required = true)@PathParam("id") Integer id) {
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




















