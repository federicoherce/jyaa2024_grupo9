package bd;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="stock_productos_terminados")
public class CanalDeVenta {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(nullable=false, unique=true)
	private String nombre;
	
	@Column(nullable=false, length=64)
	private String ubicacion;
	
	private boolean activo;
	
	
	public CanalDeVenta() {}
	
	
	public CanalDeVenta(String nombre, String ubicacion) {
		this.nombre = nombre;
		this.ubicacion = ubicacion;
		activo = true;
	}



	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getUbicacion() {
		return ubicacion;
	}
	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}
	public boolean isActivo() {
		return activo;
	}
	public void setActivo(boolean activo) {
		this.activo = activo;
	}
	
	
}
