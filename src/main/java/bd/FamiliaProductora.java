package bd;

import java.time.LocalDate;

import javax.persistence.*;

@Entity
@Table(name = "familias_productoras")
public class FamiliaProductora {
	
	
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private int id;

@Column(unique=true, nullable=false, length=64)
private String nombre;

@Column(nullable=false, updatable=false)
private LocalDate fechaInicio;

@Column(nullable=false, updatable=false)
private String zona;

private boolean activo;





public FamiliaProductora() {
}


public FamiliaProductora(String nombre, LocalDate fecha_inicio, String zona) {
	this.nombre = nombre;
	this.fechaInicio = fecha_inicio;
	this.zona = zona;
	this.activo = true;
}
public String getNombre() {
	return nombre;
}
public void setNombre(String nombre) {
	this.nombre = nombre;
}
public LocalDate getFechaInicio() {
	return fechaInicio;
}
public void setFechaInicio(LocalDate fecha_inicio) {
	this.fechaInicio = fecha_inicio;
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
