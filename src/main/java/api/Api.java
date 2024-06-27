package api;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import bd.CanalDeVenta;
import bd.FamiliaProductora;
import bd.Insumo;
import bd.ItemDeMateriaPrima;
import bd.Lote;
import bd.MateriaPrima;
import bd.StockProductoTerminado;
import bd.Usuario;
import dao.CanalDeVentaDAO;
import dao.FamiliaProductoraDAO;
import dao.InsumoDAO;
import dao.ItemDeMateriaPrimaDAO;
import dao.LoteDAO;
import dao.MateriaPrimaDAO;
import dao.StockProductoTerminadoDAO;
import dao.UsuarioDAO;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;

@Path("/cargarBD")
public class Api {
	@Inject
	private UsuarioDAO usuarioDAO;
	
	@Inject
	private FamiliaProductoraDAO familiaDAO;
	
	@Inject
	private MateriaPrimaDAO materiaDAO;
	
	@Inject
	private LoteDAO loteDAO;
	
	@Inject
	private ItemDeMateriaPrimaDAO itemDAO;
	
	@Inject
	private StockProductoTerminadoDAO stockDAO;
	
	@Inject
	private CanalDeVentaDAO canalDAO;
	
	@Inject
	private InsumoDAO insumoDAO;

	
	@GET
	public Response cargar() {
    	Usuario user = new Usuario("jose@gmail.com", "Jose", "Perez", "1234");
    	usuarioDAO.persist(user);
    	
    	LocalDate date = LocalDate.of(2020, 1, 8);
    	FamiliaProductora familia = new FamiliaProductora("flia", date, "centro");
    	familiaDAO.persist(familia);

    	LocalDate compra = LocalDate.of(2024, 5, 25);
    	LocalDate vencimiento = LocalDate.of(2024, 6, 25);
    	MateriaPrima materia = new MateriaPrima("Naranjas", 50, compra, vencimiento, 1000, "heladera", familia);
    	MateriaPrima materia2 = new MateriaPrima("Azucar", 10, compra, vencimiento, 750, "alancena", familia);
    	materiaDAO.persist(materia);
    	materiaDAO.persist(materia2); 
    	
    	Lote lote = new Lote("Mermelada de naranja", "A001", compra, 20, 20000, user);
    	loteDAO.persist(lote);
    	
    	ItemDeMateriaPrima item = new ItemDeMateriaPrima(25, lote, materia);
    	ItemDeMateriaPrima item2 = new ItemDeMateriaPrima(2, lote, materia2);
    	itemDAO.persist(item);
    	itemDAO.persist(item2);
    	
    	List<ItemDeMateriaPrima> lista = new ArrayList<>();
    	lista.add(item);
    	lista.add(item2);
    	
    	lote.setMateriaPrima(lista);
    	loteDAO.update(lote);
    
    	StockProductoTerminado stock = new StockProductoTerminado("Mermelada Naranjas 680cc", LocalDate.of(2024, 6, 3), 70.0, LocalDate.of(2024, 12, 3), 20);
    	stock.setLote(lote);
    	stockDAO.persist(stock);
    	
    	CanalDeVenta canal = new CanalDeVenta("canal predeterminado", "La Plata");
    	canalDAO.persist(canal);
    	canal.agregarProductoTerminado(stock);
    	canalDAO.update(canal);
    	
    	
    	Insumo insumo = new Insumo("Frascos 360cc", 200, 45.0);
    	Insumo otroInsumo = new Insumo("Tapitas", 1000, 50);
    	insumoDAO.persist(insumo);
    	insumoDAO.persist(otroInsumo);

    	return Response.ok().build();
	}

}
