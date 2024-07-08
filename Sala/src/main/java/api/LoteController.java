package api;

import java.util.List;
import org.json.JSONObject;
import bd.Lote;
import dao.LoteDAO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

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
	
	
}
