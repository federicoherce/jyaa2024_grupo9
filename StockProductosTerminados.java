package p1;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;



@Entity
@Table(name="stock_productos_terminados")
public class StockProductosTerminados {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String nombre;
	private Date fecha_envasado;
	private double costo_unidad;
	private double precio_venta;
	private Date fecha_vencimiento;
	private int cantidad_productos;
	private boolean activo;
	List<Insumo> insumos;
	private Lote lote;
	
}
