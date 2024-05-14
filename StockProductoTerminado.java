package p1;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;



@Entity
@Table(name="stocks_productos_terminados")
public class StockProductoTerminado {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(nullable=false, length=64)
	private String nombre;
	
	@Column(nullable=false)
	private Date fecha_envasado;
	
	@Column(nullable=false)
	private double costo_unidad;
	
	@Column(nullable=false)
	private double precio_venta;
	
	@Column(nullable=false)
	private Date fecha_vencimiento;
	
	@Column(nullable=false)
	private int cantidad_productos;
	
	private boolean activo;
	
	@OneToMany(mappedBy = "stock_producto_terminado", fetch = FetchType.LAZY)
	private List<Insumo> insumos;
	
	private Lote lote;

	
	public StockProductoTerminado() {}
	
	
	
	public StockProductoTerminado(String nombre, Date fecha_envasado, double costo_unidad, double precio_venta,
			Date fecha_vencimiento, int cantidad_productos, Lote lote) {
		this.nombre = nombre;
		this.fecha_envasado = fecha_envasado;
		this.costo_unidad = costo_unidad;
		this.precio_venta = precio_venta;
		this.fecha_vencimiento = fecha_vencimiento;
		this.cantidad_productos = cantidad_productos;
		this.insumos = insumos;
		this.lote = lote;
		activo = true;
	}




	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Date getFecha_envasado() {
		return fecha_envasado;
	}

	public void setFecha_envasado(Date fecha_envasado) {
		this.fecha_envasado = fecha_envasado;
	}

	public double getCosto_unidad() {
		return costo_unidad;
	}

	public void setCosto_unidad(double costo_unidad) {
		this.costo_unidad = costo_unidad;
	}

	public double getPrecio_venta() {
		return precio_venta;
	}

	public void setPrecio_venta(double precio_venta) {
		this.precio_venta = precio_venta;
	}

	public Date getFecha_vencimiento() {
		return fecha_vencimiento;
	}

	public void setFecha_vencimiento(Date fecha_vencimiento) {
		this.fecha_vencimiento = fecha_vencimiento;
	}

	public int getCantidad_productos() {
		return cantidad_productos;
	}

	public void setCantidad_productos(int cantidad_productos) {
		this.cantidad_productos = cantidad_productos;
	}

	public boolean isActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}

	public List<Insumo> getInsumos() {
		return insumos;
	}

	public void setInsumos(List<Insumo> insumos) {
		this.insumos = insumos;
	}

	public Lote getLote() {
		return lote;
	}

	public void setLote(Lote lote) {
		this.lote = lote;
	}
	
	
	
	
	
}