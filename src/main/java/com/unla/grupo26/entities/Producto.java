package com.unla.grupo26.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Setter
@Getter
public class Producto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idProducto;

	@Column(nullable = false)
	private String nombre;

	@Column(nullable = false)
	private String descripcionProducto;

	@Column(nullable = false)
	private float precio;

	@Column(nullable = false)
	private float costo;

	@Column(nullable = false)
	private long codigo;

	@OneToOne(mappedBy = "producto", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Detalle detalle;

	public Producto(String nombre, String descripcionProducto, float precio, float costo, long codigo) {
		super();
		this.nombre = nombre;
		this.descripcionProducto = descripcionProducto;
		this.precio = precio;
		this.costo = costo;
		this.codigo = codigo;
	}

}
