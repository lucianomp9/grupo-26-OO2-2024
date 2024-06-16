package entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Detalle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idDetalle;

    @Column(nullable = false)
    private int cantidad;
    @Column(nullable = false)
    private float precioCompra;
    

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idCompra")
    private Compra compra;

    @Column(nullable = false)
    private Producto producto;

    public Detalle(int cantidad, Compra compra, float precioCompra, Producto producto) {
        this.cantidad = cantidad;
        this.compra = compra;
        this.precioCompra = precioCompra;
        this.producto = producto;
    }
}