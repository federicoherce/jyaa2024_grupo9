package api;

import java.util.List;

import javax.persistence.PersistenceException;

import org.hibernate.PropertyValueException;

import bd.Insumo;
import bd.ItemDeInsumo;
import bd.Lote;
import bd.StockProductoTerminado;
import dao.InsumoDAO;
import dao.ItemDeInsumoDAO;
import dao.LoteDAO;
import dao.StockProductoTerminadoDAO;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/productos")
public class ProductoElaborado {

	@Inject
	private StockProductoTerminadoDAO stockDao;
	
	@Inject
	private LoteDAO loteDao;
	
	@Inject
	private ItemDeInsumoDAO itemDao;
	
	@Inject
	private InsumoDAO insumoDao;
	
	@GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Obtener un producto por su ID")
    public Response getProductoById(@PathParam("id") int id) {
    	StockProductoTerminado producto = stockDao.findActiveById(id);
        if (producto == null) {
        	String mensaje= "No se encontró el producto";
        	return Response.status(Response.Status.NOT_FOUND).entity(mensaje).build();
        }
        return Response.ok(producto).build();
    }
		
	@Path("/{productoId}/agregarInsumos")
	@POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Agregar insumos a un stock de productos")
	public Response agregarInsumos(@PathParam("productoId") int productoId, List<ItemDeInsumo> insumos) {
		StockProductoTerminado producto = stockDao.findActiveById(productoId);
		if (producto == null)
			return Response.status(Response.Status.NOT_FOUND).build();
		double valorInsumos = 0;
		for (ItemDeInsumo item : insumos) {
			ItemDeInsumo itemInsumo = new ItemDeInsumo(item.getCantidad(), producto, item.getInsumo());
			Insumo insumo = insumoDao.findActiveById(item.getInsumo().getId());
			insumo.setCantidad(insumo.getCantidad() - item.getCantidad());
			valorInsumos += item.getCantidad() * insumo.getCostoUnitario();
			insumoDao.update(insumo);
			itemDao.persist(itemInsumo);		
		}
		producto.setInsumos(insumos);
		producto.setCostoTotal(producto.getCostoTotal() + valorInsumos);
		stockDao.update(producto);
		return Response.ok().entity("Insumos agregados").build();
	}
	
	
	@Path("/{idLote}")
	@POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Crear un producto")
	public Response entregarProducto(StockProductoTerminado producto, @PathParam("idLote") int idLote) {
    	try {
    		Lote lote = loteDao.findActiveById(idLote);
    		if (lote == null)
    			return Response.status(Response.Status.NOT_FOUND).entity("Lote invalido").build();
    		producto.setLote(lote);
    		producto.setCostoTotal(lote.getCostoLote());
    		stockDao.persist(producto);
    		lote.setActivo(false);
    		loteDao.update(lote);
    	} catch (PersistenceException e) {
            Throwable cause = e.getCause();
            if (cause instanceof PropertyValueException) 
            	return Response.status(Response.Status.CONFLICT).entity("Falta completar campo/s obligatorio/s").build();
    }
    	return Response.status(Response.Status.CREATED).entity(producto).build();
	}
}
