package bd;


import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "lotes")
public class Lote {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(unique=true, nullable=false, length=32, name="codigo", updatable=false)
    private String codigo;
    
    @Column(unique=false, nullable=false, length=64, name="nombre")
    private String nombre;
    
    @Column(unique=false, nullable=false, name="fecha_elaboracion", updatable=false)
    private Date fecha_elaboracion;
    
    @Column(unique=false, nullable=false, name="cantidad_producida")
    private double cantidad_producida;
    
    @Column(unique=false, nullable=false, name="costo_lote")
    private double costo_lote;
    
    @ManyToMany
    @JoinTable(name = "lote_materia",
    joinColumns = @JoinColumn(name = "lote_id"),
    inverseJoinColumns = @JoinColumn(name = "materia_id"))
    private List<MateriaPrima> materia_prima;
    
    private boolean activo;

    
	public Lote() {
		
	}


	public Lote(String nombre, String codigo, Date fecha_elaboracion, double cantidad_producida, double costo_lote,
			List<MateriaPrima> materia_prima) {
		this.nombre = nombre;
		this.codigo = codigo;
		this.fecha_elaboracion = fecha_elaboracion;
		this.cantidad_producida = cantidad_producida;
		this.costo_lote = costo_lote;
		this.materia_prima = materia_prima;
		this.activo = true;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String getCodigo() {
		return codigo;
	}


	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}


	public Date getFecha_elaboracion() {
		return fecha_elaboracion;
	}


	public void setFecha_elaboracion(Date fecha_elaboracion) {
		this.fecha_elaboracion = fecha_elaboracion;
	}


	public double getCantidad_producida() {
		return cantidad_producida;
	}


	public void setCantidad_producida(double cantidad_producida) {
		this.cantidad_producida = cantidad_producida;
	}


	public double getCosto_lote() {
		return costo_lote;
	}


	public void setCosto_lote(double costo_lote) {
		this.costo_lote = costo_lote;
	}


	public List<MateriaPrima> getMateria_prima() {
		return materia_prima;
	}


	public void setMateria_prima(List<MateriaPrima> materia_prima) {
		this.materia_prima = materia_prima;
	}


	public boolean isActivo() {
		return activo;
	}


	public void setActivo(boolean activo) {
		this.activo = activo;
	}
	
	
	
	
    
    
}
