package modelo;

import java.time.LocalDate;

import javax.persistence.*;

@Entity
@Table(name = "familias_productoras")
public class FamiliaProductora {
	
	
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private int id;
@Column(unique=true, nullable=false, length=64, name="nombre", updatable=true)
private String nombre ;
@Column(unique=false, nullable=false, length=64, name="fecha_inicio", updatable=false)
private LocalDate fecha_inicio;
@Column(unique=false, nullable=false, length=64, name="fecha_inicio", updatable=false)
private String zona;
private boolean activo;





public FamiliaProductora() {
}


public FamiliaProductora(String nombre, LocalDate fecha_inicio, String zona) {
	this.nombre = nombre;
	this.fecha_inicio = fecha_inicio;
	this.zona = zona;
	this.activo = true;
}
public String getNombre() {
	return nombre;
}
public void setNombre(String nombre) {
	this.nombre = nombre;
}
public LocalDate getFecha_inicio() {
	return fecha_inicio;
}
public void setFecha_inicio(LocalDate fecha_inicio) {
	this.fecha_inicio = fecha_inicio;
}
public String getZona() {
	return zona;
}
public void setZona(String zona) {
	this.zona = zona;
}
public boolean isActivo() {
	return activo;
}
public void setActivo(boolean activo) {
	this.activo = activo;
}



}
