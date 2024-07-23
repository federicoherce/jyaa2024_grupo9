package api;

import java.util.List;
import org.json.JSONObject;

import bd.Insumo;
import bd.Lote;
import dao.LoteDAO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import requests.LoteRequest;

@Path("/lotes")
public class LoteController {
	
	@Inject
	private LoteDAO loteDao;

	@GET
	@Path("/all")
	@Produces(MediaType.APPLICATION_JSON)
	@Operation(summary = "Obtener todos los lotes")
	@ApiResponses(value = {
	    @ApiResponse(responseCode = "200", description = "Lotes encontrados"),
	    @ApiResponse(responseCode = "404", description = "Lotes no encontrados")
	})
    public Response getAllLotes() {
    	List<Lote> lotes = loteDao.findAll();
        if (lotes == null) {
        	String mensaje = new JSONObject().put("message", "No hay lotes disponibles").toString();
        	return Response.status(Response.Status.NOT_FOUND).entity(mensaje).build();
        }
        return Response.ok(lotes).build();
    }
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Operation(summary = "Creacion de un nuevo lote")
	@ApiResponses(value = {
	    @ApiResponse(responseCode = "201", description = "Lote creado correctamente",
	        content = @Content(mediaType = "application/json",
	        schema = @Schema(implementation = Lote.class))),
	    @ApiResponse(responseCode = "409", description = "Conflicto de datos")
	})
    public Response crearLote(@Parameter(description = "Datos del lote", required = true) LoteRequest lote) {
		Lote aux = new Lote(lote.getNombre(), lote.getCodigo(), lote.getFechaElaboracion(), lote.getCantidadProducida(), )
	}
	
	
}
