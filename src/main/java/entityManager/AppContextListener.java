package entityManager;


import bd.Usuario;
import bd.Lote;
import bd.MateriaPrima;
import bd.FamiliaProductora;
import bd.ItemDeMateriaPrima;
import dao.UsuarioDAO;
import dao.LoteDAO;
import dao.MateriaPrimaDAO;
import dao.FamiliaProductoraDAO;
import dao.ItemDeMateriaPrimaDAO;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@WebListener
public class AppContextListener implements ServletContextListener {


    public void contextInitialized(ServletContextEvent sce)  { 
    	UsuarioDAO usuarioDAO = new UsuarioDAO();
    	Usuario user = new Usuario("jose@gmail.com", "Jose", "Perez", "1234");
    	usuarioDAO.persist(user);
    	
    	FamiliaProductoraDAO familiaDAO = new FamiliaProductoraDAO();
    	LocalDate date = LocalDate.of(2020, 1, 8);
    	FamiliaProductora familia = new FamiliaProductora("flia", date, "centro");
    	familiaDAO.persist(familia);
    	
    	MateriaPrimaDAO materiaDAO = new MateriaPrimaDAO();
    	LocalDate compra = LocalDate.of(2024, 5, 25);
    	LocalDate vencimiento = LocalDate.of(2024, 6, 25);
    	MateriaPrima materia = new MateriaPrima("Naranjas", 50, compra, vencimiento, 1000, "heladera", familia);
    	MateriaPrima materia2 = new MateriaPrima("Azucar", 10, compra, vencimiento, 750, "alancena", familia);
    	materiaDAO.persist(materia);
    	materiaDAO.persist(materia2); 
    	
    	LoteDAO loteDAO = new LoteDAO();
    	Lote lote = new Lote("Mermelada de naranja", "A001", compra, 20, 20000, user);
    	loteDAO.persist(lote);
    	
    	ItemDeMateriaPrimaDAO itemDAO = new ItemDeMateriaPrimaDAO();
    	ItemDeMateriaPrima item = new ItemDeMateriaPrima(25, lote, materia);
    	ItemDeMateriaPrima item2 = new ItemDeMateriaPrima(2, lote, materia2);
    	itemDAO.persist(item);
    	itemDAO.persist(item2);
    	
    	List<ItemDeMateriaPrima> lista = new ArrayList<>();
    	lista.add(item);
    	lista.add(item2);
    	
    	lote.setMateriaPrima(lista);
    	loteDAO.update(lote);
    	
    }


    public void contextDestroyed(ServletContextEvent sce)  { 
        EntityManagerFactorySingleton.close();
    }
	
}
