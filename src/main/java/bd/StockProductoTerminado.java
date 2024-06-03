package bd;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "stocks_productos_terminados")
public class StockProductoTerminado {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(nullable = false, length = 64)
	private String nombre;

	@Column(nullable = false)
	private Date fechaEnvasado;

	@Column(nullable = false)
	private double costoUnidad;

	@Column(nullable = false)
	private double precioVenta;

	@Column(nullable = false)
	private Date fechaVencimiento;

	@Column(nullable = false)
	private int cantidadProductos;

	private boolean activo;

	@OneToMany(mappedBy = "stock", fetch = FetchType.LAZY)
	private List<ItemDeInsumo> insumos;

	@OneToOne
	@JoinColumn(name = "lote_id")
	private Lote lote;

	public StockProductoTerminado() {
	}

	public StockProductoTerminado(String nombre, Date fecha_envasado, double costo_unidad, double precio_venta,
			Date fecha_vencimiento, int cantidad_productos, Lote lote) {
		this.nombre = nombre;
		this.fechaEnvasado = fecha_envasado;
		this.costoUnidad = costo_unidad;
		this.precioVenta = precio_venta;
		this.fechaVencimiento = fecha_vencimiento;
		this.cantidadProductos = cantidad_productos;
		this.lote = lote;
		this.insumos = new ArrayList<ItemDeInsumo>();
		this.activo = true;
	}


	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Date getFechaEnvasado() {
		return fechaEnvasado;
	}

	public void setFechaEnvasado(Date fecha_envasado) {
		this.fechaEnvasado = fecha_envasado;
	}

	public double getCostoUnidad() {
		return costoUnidad;
	}

	public void setCostoUnidad(double costo_unidad) {
		this.costoUnidad = costo_unidad;
	}

	public double getPrecioVenta() {
		return precioVenta;
	}

	public void setPrecioVenta(double precio_venta) {
		this.precioVenta = precio_venta;
	}

	public Date getFecha_vencimiento() {
		return fechaVencimiento;
	}

	public void setFechaVencimiento(Date fecha_vencimiento) {
		this.fechaVencimiento = fecha_vencimiento;
	}

	public int getCantidadProductos() {
		return cantidadProductos;
	}

	public void setCantidadProductos(int cantidad_productos) {
		this.cantidadProductos = cantidad_productos;
	}

	public boolean isActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}

	public List<ItemDeInsumo> getInsumos() {
		return insumos;
	}

	public void setInsumos(List<ItemDeInsumo> insumos) {
		this.insumos = insumos;
	}

	public Lote getLote() {
		return lote;
	}

	public void setLote(Lote lote) {
		this.lote = lote;
	}

}