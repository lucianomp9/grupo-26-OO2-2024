package com.unla.grupo26.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Setter
@Getter
//Pedido aprovisionamiento
public class SupplyOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String user;

    private int quantityOrdered;

    private String supplier;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id", referencedColumnName = "product_id")
    private Product product;

    public SupplyOrder(String user, int quantityOrdered, String supplier, Product product) {
        this.user = user;
        this.product = product;
        this.supplier = supplier;
        this.quantityOrdered = quantityOrdered;
    }
}