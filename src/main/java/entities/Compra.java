package entities;

import java.time.LocalDate;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
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
	private int idCompra;

	@Column(nullable = false)
	private LocalDate fechaCompra;

	@OneToOne(mappedBy = "compra", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Detalle detalle;

	@Column(nullable = false)
	private Usuario cliente;

	public Compra( LocalDate fechaCompra, Detalle detalle,Usuario cliente) {
		super();
		this.fechaCompra = fechaCompra;
		this.detalle = detalle;
		this.cliente = cliente;
	}

}
