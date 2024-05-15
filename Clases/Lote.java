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
    private Date fechaElaboracion;
    
    @Column(unique=false, nullable=false, name="cantidad_producida")
    private double cantidadProducida;
    
    @Column(unique=false, nullable=false, name="costo_lote")
    private double costoLote;
    
    @OneToMany
    @JoinColumn(mappedBy = "items_de_materia_prima")
    private List<ItemDeMateriaPrima> listaItemsDeMateriaPrima;
    
    private boolean activo;

    
	public Lote() {
		
	}


	public Lote(String nombre, String codigo, Date fecha_elaboracion, double cantidad_producida, double costo_lote,
			List<MateriaPrima> materia_prima) {
		this.nombre = nombre;
		this.codigo = codigo;
		this.fechaElaboracion = fecha_elaboracion;
		this.cantidadProducida = cantidad_producida;
		this.costoLote = costo_lote;
		this.materiaPrima = materia_prima;
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


	public Date getFechaElaboracion() {
		return fechaElaboracion;
	}


	public void setFechaElaboracion(Date fecha_elaboracion) {
		this.fechaElaboracion = fecha_elaboracion;
	}


	public double getCantidadProducida() {
		return cantidadProducida;
	}


	public void setCantidadProducida(double cantidad_producida) {
		this.cantidadProducida = cantidad_producida;
	}


	public double getCostoLote() {
		return costoLote;
	}


	public void setCostoLote(double costo_lote) {
		this.costoLote = costo_lote;
	}


	public List<MateriaPrima> getMateriaPrima() {
		return materiaPrima;
	}


	public void setMateriaPrima(List<MateriaPrima> materia_prima) {
		this.materiaPrima = materia_prima;
	}


	public boolean isActivo() {
		return activo;
	}


	public void setActivo(boolean activo) {
		this.activo = activo;
	}
	
	
	
	
    
    
}
