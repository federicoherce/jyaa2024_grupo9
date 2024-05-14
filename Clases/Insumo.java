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
    
    @Column(unique=true, nullable=false, length=32, name="nombre", updatable=false)
    private String nombre;
    
    @Column(unique=false, nullable=false, length=32, name="cantidad", updatable=true)
    private double cantidad;
    
    @Column(unique=false, nullable=false, length=32, name="costo_unitario", updatable=true)
    private double costo_unitario;

    private boolean activo;
    
    public Insumo() {
    	
    }

	public Insumo(String nombre, double cantidad, double costo_unitario) {
		this.nombre = nombre;
		this.cantidad = cantidad;
		this.costo_unitario = costo_unitario;
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

	public double getCosto_unitario() {
		return costo_unitario;
	}

	public void setCosto_unitario(double costo_unitario) {
		this.costo_unitario = costo_unitario;
	}

	public boolean isActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}
    
	
	
    
    
    
}
