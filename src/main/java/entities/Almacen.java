package entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@NoArgsConstructor
@Setter
@Getter
public class Almacen extends Stock {

    @Column(nullable = false)
    private int cantidadDisponible;

    @Column(nullable = false)
    private int cantidadMinima;

    @OneToMany(fetch= FetchType.LAZY, mappedBy="lote")
    private Set<Lote> lotes = new HashSet<>();;

    public Almacen(int cantidadDisponible, int cantidadMinima){
        this.cantidadDisponible = cantidadDisponible;
        this.cantidadMinima = cantidadMinima;
    }

    public Almacen(int cantidadDisponible, int cantidadMinima, Set<Lote> lotes){
        this.cantidadDisponible = cantidadDisponible;
        this.cantidadMinima = cantidadMinima;
        this.lotes = lotes;
    }
}
