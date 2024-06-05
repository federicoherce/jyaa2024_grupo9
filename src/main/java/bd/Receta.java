package bd;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "recetas")
public class Receta {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(unique = true, nullable = false, length = 32, name = "titulo", updatable = true)
	private String titulo;

	@Column(unique = false, nullable = false, length = 512, name = "texto")
	private String texto;

	@OneToOne
	@JoinColumn(name = "usuario_id")
	private Usuario usuario;

	private boolean activo;

	public Receta() {

	}

	public Receta(String titulo, String texto, Usuario usuario) {
		this.titulo = titulo;
		this.texto = texto;
		this.usuario = usuario;
		this.activo = true;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public boolean isActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}

}
