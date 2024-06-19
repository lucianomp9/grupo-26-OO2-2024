package com.unla.grupo26.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;


@Entity
@NoArgsConstructor
@Setter
@Getter
// Lote
public class Batch {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long batchId;

    @CreationTimestamp
    private LocalDate receptionDate;

    @Column(nullable = false)
    private int quantityReceived;

    @Column(nullable = false)
    private float batchPrice;

    @Column(nullable = false, length = 60)
    private String supplier;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "storage_id", nullable = false)
    private Storage storage;

    @OneToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    public Batch(LocalDate receptionDate, int quantityReceived, float batchPrice, String supplier) {
        this.receptionDate = receptionDate;
        this.quantityReceived = quantityReceived;
        this.batchPrice = batchPrice;
        this.supplier = supplier;
    }

    public void setStorage(Storage storage) {
        this.storage = storage;
    }

	public void setProduct(Product product) {
		this.product = product;
	}
    
    
}