package modelo;

import java.time.LocalDate;
import javax.persistence.*;

@Entity
@Table(name = "materias_primas")
public class MateriaPrima {
	
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private int id;

@Column(unique=true, nullable=false, length=64, updatable=true)
private String nombre;

@Column(nullable=false)
private double peso;

@Columnnullable=false, length=64)
private LocalDate fecha_compra;

@Columnnullable=false, length=64)
private LocalDate fecha_vencimiento; 

@Column(nullable=false)
private double costo_por_kg;

@Column(nullable=false, length=64)
private String forma_almacenamiento;

private boolean activo;

@ManyToOne
@JoinColumn(name = "productor_id") 
private FamiliaProductora productor;

public MateriaPrima(String nombre, double peso, LocalDate fecha_compra, LocalDate fecha_vencimiento,
		double costo_por_kg, String forma_almacenamiento, FamiliaProductora productor) {
	super();
	this.nombre = nombre;
	this.peso = peso;
	this.fecha_compra = fecha_compra;
	this.fecha_vencimiento = fecha_vencimiento;
	this.costo_por_kg = costo_por_kg;
	this.forma_almacenamiento = forma_almacenamiento;
	this.productor = productor;
	this.activo = true;
}

public MateriaPrima() {
}

public String getNombre() {
	return nombre;
}

public void setNombre(String nombre) {
	this.nombre = nombre;
}

public double getPeso() {
	return peso;
}

public void setPeso(double peso) {
	this.peso = peso;
}

public LocalDate getFecha_compra() {
	return fecha_compra;
}

public void setFecha_compra(LocalDate fecha_compra) {
	this.fecha_compra = fecha_compra;
}

public LocalDate getFecha_vencimiento() {
	return fecha_vencimiento;
}

public void setFecha_vencimiento(LocalDate fecha_vencimiento) {
	this.fecha_vencimiento = fecha_vencimiento;
}

public double getCosto_por_kg() {
	return costo_por_kg;
}

public void setCosto_por_kg(double costo_por_kg) {
	this.costo_por_kg = costo_por_kg;
}

public String getForma_almacenamiento() {
	return forma_almacenamiento;
}

public void setForma_almacenamiento(String forma_almacenamiento) {
	this.forma_almacenamiento = forma_almacenamiento;
}

public boolean isActivo() {
	return activo;
}

public void setActivo(boolean activo) {
	this.activo = activo;
}

public FamiliaProductora getProductor() {
	return productor;
}

public void setProductor(FamiliaProductora productor) {
	this.productor = productor;
}




	

}
