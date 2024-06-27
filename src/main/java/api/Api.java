package api;


import java.time.LocalDate;

import bd.FamiliaProductora;
import bd.ItemDeMateriaPrima;
import bd.Lote;
import bd.MateriaPrima;
import bd.Usuario;
import dao.FamiliaProductoraDAO;
import dao.ItemDeMateriaPrimaDAO;
import dao.LoteDAO;
import dao.MateriaPrimaDAO;
import dao.UsuarioDAO;
//import dao.IUsuarioDAO;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/cargarBD")
public class Api {
	
	@Inject
	private UsuarioDAO userDao;
	
	@Inject
	private FamiliaProductoraDAO familiaDAO;
	
	@Inject
	private MateriaPrimaDAO materiaDAO;
	
	@Inject
	private LoteDAO loteDAO;
	
	@Inject
	private ItemDeMateriaPrimaDAO itemDAO;
	
	
	
	//  private IUsuarioDAO userDao;
	
	@GET
    public Response sayHello() {
    	Usuario user = new Usuario("HOLA@gmail.com", "Jose", "Perez", "1234");
    	userDao.persist(user);
    	
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
    	
    	
    	
    	return Response.ok().build();
    }
		
	
}