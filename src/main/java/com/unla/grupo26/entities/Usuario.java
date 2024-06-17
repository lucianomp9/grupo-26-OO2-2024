package com.unla.grupo26.entities;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idUsuario;

	@Column(nullable = false, unique = true)
	private String usuario;

    @Column(nullable = false)
    private String nombre;

	@Column(nullable = false)
	private String apellido;

	@Column(nullable = false)
	private String clave;

	@Column(nullable = false, unique = true)
	private int dni;

	@Column(nullable = false)
	private boolean activo;

	@CreationTimestamp
	private LocalDateTime fechaAlta;

	@UpdateTimestamp
	private LocalDateTime fechaActualizacion;

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

	public long getIdUsuario() {
		return idUsuario;
	}

	public String getUsuario() {
		return usuario;
	}


	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}


	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public int getDni() {
		return dni;
	}

	public void setDni(int dni) {
		this.dni = dni;
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
