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
public class Sale {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idSale;

	@Column(nullable = false)
	private LocalDate saleDate;

	@ManyToOne
	@JoinColumn(name = "id")
	private User user;

	@OneToOne(mappedBy = "sale", cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = false)
	private SaleItem saleItem;

	public Sale(LocalDate saleDate, User user) {
		super();
		this.saleDate = saleDate;
		this.user = user;
	}

}
