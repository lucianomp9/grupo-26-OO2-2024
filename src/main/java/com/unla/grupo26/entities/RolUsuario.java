package com.unla.grupo26.entities;

import jakarta.persistence.*;

@Entity
public class RolUsuario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idRolUsuario;
	
	@Column(nullable = false)
	private String rol;

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

}
