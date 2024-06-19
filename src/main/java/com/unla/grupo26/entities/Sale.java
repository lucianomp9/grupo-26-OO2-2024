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

	public Sale() {
        // Constructor vacío para JPA
    }

	public Sale(LocalDate saleDate, User user) {
        this.saleDate = saleDate;
        this.user = user;
    }

	public long getIdSale() {
		return idSale;
	}

	public void setIdSale(long idSale) {
		this.idSale = idSale;
	}

	public LocalDate getSaleDate() {
		return saleDate;
	}

	public void setSaleDate(LocalDate saleDate) {
		this.saleDate = saleDate;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public SaleItem getSaleItem() {
		return saleItem;
	}

	public void setSaleItem(SaleItem saleItem) {
		this.saleItem = saleItem;
	}

	
}
