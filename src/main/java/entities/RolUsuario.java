package entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity
public class RolUsuario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idRolUsuario;
	
	@Column(nullable = false)
	private String rol;
	
	@OneToOne(mappedBy = "rolUsuario",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Usuario usuario;
	
	//Constructor vacio
	public RolUsuario() {}


	//Constructor con parámetros
	public RolUsuario( String rol) {
		super();
		this.rol = rol;
	}

	//Getters and Setters
	public int getIdRolUsuario() {
		return idRolUsuario;
	}


	public void setIdRolUsuario(int idRolUsuario) {
		this.idRolUsuario = idRolUsuario;
	}


	public String getRol() {
		return rol;
	}


	public void setRol(String rol) {
		this.rol = rol;
	}


	public Usuario getUsuario() {
		return usuario;
	}


	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
}
