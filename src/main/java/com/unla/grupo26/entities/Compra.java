package com.unla.grupo26.entities;

import java.time.LocalDate;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Setter
@Getter
public class Compra {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idCompra;

	@Column(nullable = false)
	private LocalDate fechaCompra;

	@OneToOne(mappedBy = "compra", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Detalle detalle;

	@ManyToOne
	@JoinColumn(name = "idUsuario")
	private Usuario usuario;

	public Compra(LocalDate fechaCompra, Detalle detalle, Usuario usuario) {
		super();
		this.fechaCompra = fechaCompra;
		this.detalle = detalle;
		this.usuario = usuario;
	}

}
