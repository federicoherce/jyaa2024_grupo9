package api;

import java.util.List;

import javax.persistence.PersistenceException;

import org.json.JSONObject;

import bd.Insumo;
import bd.ItemDeInsumo;
import bd.Lote;
import bd.StockProductoTerminado;
import dao.InsumoDAO;
import dao.ItemDeInsumoDAO;
import dao.LoteDAO;
import dao.StockProductoTerminadoDAO;
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
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import requests.InsumoRequest;
import requests.ProductoTerminadoRequest;

@Path("/productos")
public class ProductoElaboradoController {

	@Inject
	private StockProductoTerminadoDAO stockDao;
	
	@Inject
	private LoteDAO loteDao;
	
	
	@Inject
	private ItemDeInsumoDAO itemDao;
	
	@Inject
	private InsumoDAO insumoDao;
	
	
	
	@GET
	@Path("/all")
	@Produces(MediaType.APPLICATION_JSON)
	@Operation(summary = "Obtener todos los productos")
	@ApiResponses(value = {
	    @ApiResponse(responseCode = "200", description = "Productos encontrados"),
	    @ApiResponse(responseCode = "404", description = "Productos no encontrados")
	})
    public Response getAllProducts() {
    	List<StockProductoTerminado> productos = stockDao.findAll();
        if (productos == null) {
        	String mensaje = new JSONObject().put("message", "Productos no encontrados").toString();
        	return Response.status(Response.Status.NOT_FOUND).entity(mensaje).build();
        }
        return Response.ok(productos).build();
    }
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Operation(summary = "Obtener un producto por su ID")
	@ApiResponses(value = {
	    @ApiResponse(responseCode = "200", description = "Producto encontrado",
	        content = @Content(mediaType = "application/json",
	        schema = @Schema(implementation = StockProductoTerminado.class))),
	    @ApiResponse(responseCode = "404", description = "Producto no encontrado")
	})
    public Response getProductoById(@Parameter(description = "ID del producto", required = true) @PathParam("id") int id) {
    	StockProductoTerminado producto = stockDao.findActiveById(id);
        if (producto == null) {
        	String mensaje= "No se encontró el producto";
        	return Response.status(Response.Status.NOT_FOUND).entity(mensaje).build();
        }
        return Response.ok(producto).build();
    }
		
	
	@Path("/{idLote}")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Operation(summary = "Creacion de un producto a partir de un ID de lote")
	@ApiResponses(value = {
	    @ApiResponse(responseCode = "201", description = "Producto creado",
	        content = @Content(mediaType = "application/json",
	        schema = @Schema(implementation = StockProductoTerminado.class))),
	    @ApiResponse(responseCode = "409", description = "Conflicto de datos")
	})
	public Response entregarProducto(@Parameter(description = "ID del lote", required = true) @PathParam("idLote") int idLote,
			@Parameter(description="Datos del producto") StockProductoTerminado prod) {
    	try {
    		Lote lote = loteDao.findActiveById(idLote);
    		if (lote == null)
    			return Response.status(Response.Status.NOT_FOUND).entity("Lote invalido").build();
    		prod.setLote(lote);
    		prod.setCostoTotal(lote.getCostoLote());
    		stockDao.persist(prod);
    		prod.getId();
    		lote.setActivo(false);
    		loteDao.update(lote);
    		return Response.status(Response.Status.CREATED).entity(prod).build();
    	} catch (PersistenceException e) {
            	return Response.status(Response.Status.CONFLICT).entity("Falta completar campo/s obligatorio/s").build();
    	} 	
	}
	
	
	@Path("/{productoId}/agregarInsumos")
	@POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Agregar insumos a un stock de productos")
	public Response agregarInsumos(@Parameter(description = "ID del producto", required = true) @PathParam("productoId") int productoId, 
	@Parameter(description = "Datos de los insumos", required = true) InsumoRequest insumoRequest) {
		StockProductoTerminado producto = stockDao.findActiveById(productoId);
        boolean insumoExistente = producto.getInsumos().stream()
                .anyMatch(insumo -> insumo.getInsumo().getId() == insumoRequest.getInsumo());
        if (insumoExistente) {
        		String mensaje = new JSONObject().put("message", "El insumo ya se encuentra asociado").toString();
                return Response.status(Response.Status.CONFLICT).entity(mensaje).build();
            }
        int idInsumo = insumoRequest.getInsumo();
		int cantidadInsumo = insumoRequest.getCantidad();
		Insumo insumo = insumoDao.findActiveById(idInsumo);
		if (insumo.getCantidad() < cantidadInsumo) {
			String mensaje = new JSONObject().put("message", "Cantidad de insumos insuficiente").toString();
            return Response.status(Response.Status.CONFLICT).entity(mensaje).build();
		}	
		ItemDeInsumo itemInsumo = new ItemDeInsumo(cantidadInsumo, producto, insumo);
		insumo.setCantidad(insumo.getCantidad() - cantidadInsumo);
		insumoDao.update(insumo);
		itemDao.persist(itemInsumo);
		producto.addInsumos(itemInsumo);
		double valorInsumo = cantidadInsumo * insumo.getCostoUnitario();
		producto.setCostoTotal(producto.getCostoTotal() + valorInsumo);
		stockDao.update(producto);
     	String mensaje = new JSONObject().put("message", "Insumos agregados").toString();
		return Response.ok().entity(mensaje).build();
	}
}
