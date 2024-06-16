package com.unla.grupo26.entities;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idUsuario;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String password;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "usuario")
	private Set<Compra> compras = new HashSet<>();

	@ManyToOne
	@JoinColumn(name = "rolUsuario_id", nullable = false)
	private RolUsuario rolUsuario;

	// Constructor vacío
    public Usuario() {
    }
    
    
    public Usuario(String nombre, String password,RolUsuario rolUsuario) {
        this.nombre = nombre;
        this.password = password;
        this.rolUsuario = rolUsuario;
    }

	public Usuario(String nombre, String password,RolUsuario rolUsuario, Set<Compra> compras) {
		this.nombre = nombre;
		this.password = password;
		this.rolUsuario = rolUsuario;
		this.compras = compras;
	}

    // Getters y Setters

	public int getIdUsuario() {
		return idUsuario;
	}


	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public RolUsuario getRolUsuario() {
		return rolUsuario;
	}


	public void setRolUsuario(RolUsuario rolUsuario) {
		this.rolUsuario = rolUsuario;
	}

    
    
}
