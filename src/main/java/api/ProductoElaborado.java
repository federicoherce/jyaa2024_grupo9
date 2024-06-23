package api;

import javax.persistence.PersistenceException;

import org.hibernate.PropertyValueException;
import org.hibernate.exception.ConstraintViolationException;

import bd.Lote;
import bd.StockProductoTerminado;
import dao.LoteDAO;
import dao.StockProductoTerminadoDAO;
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
	
	@GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProductoById(@PathParam("id") int id) {
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
	public Response entregarProducto(StockProductoTerminado producto, @PathParam("idLote") int idLote) {
    	try {
    		Lote lote = loteDao.findActiveById(idLote);
    		if (lote == null)
    			return Response.status(Response.Status.NOT_FOUND).entity("Lote invalido").build();
        	producto.setLote(lote);
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
