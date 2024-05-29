package bd;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "insumos")
public class Insumo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(unique = true, nullable = false, length = 64, updatable = false)
	private String nombre;

	@Column(unique = false, nullable = false, updatable = true)
	private double cantidad;

	@Column(unique = false, nullable = false, updatable = true)
	private double costoUnitario;

	private boolean activo;

	public Insumo() {

	}

	public Insumo(String nombre, double cantidad, double costo_unitario) {
		this.nombre = nombre;
		this.cantidad = cantidad;
		this.costoUnitario = costo_unitario;
		this.activo = true;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public double getCantidad() {
		return cantidad;
	}

	public void setCantidad(double cantidad) {
		this.cantidad = cantidad;
	}

	public double getCostoUnitario() {
		return costoUnitario;
	}

	public void setCostoUnitario(double costo_unitario) {
		this.costoUnitario = costo_unitario;
	}

	public boolean isActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}

}
