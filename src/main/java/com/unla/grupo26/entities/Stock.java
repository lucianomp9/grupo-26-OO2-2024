package com.unla.grupo26.entities;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Entity
@Setter
@Getter
public class Stock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idStock;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idProducto")
    private Producto producto;
    
    public Stock() {}
    
    public Stock(Producto producto){
        this.producto = producto;
    }
}
