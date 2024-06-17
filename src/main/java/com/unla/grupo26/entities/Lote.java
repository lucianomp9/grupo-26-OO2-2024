package com.unla.grupo26.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@NoArgsConstructor
@Setter
@Getter
public class Lote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idLote;

    @Column(nullable = false)
    private LocalDate fechaRecepcion;

    @Column(nullable = false)
    private int cantidadRecibida;

    @Column(nullable = false)
    private float precioLote;

    @Column(nullable = false, length = 60)
    private String proveedor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "almacen_id", nullable = false)
    private Almacen almacen;

    public Lote(LocalDate fechaRecepcion, int cantidadRecibida, float precioLote, String proveedor, Almacen almacen) {
        this.fechaRecepcion = fechaRecepcion;
        this.cantidadRecibida = cantidadRecibida;
        this.precioLote = precioLote;
        this.proveedor = proveedor;
        this.almacen = almacen;
    }
}
