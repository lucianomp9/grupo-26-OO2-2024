package entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Setter
@Getter
public class PedidoAprovisionamiento extends Stock {

    @Column(nullable = false, length=60)
    private String proveedor;

    @Column(nullable = false)
    private int cantidadPedida;

    public PedidoAprovisionamiento(String proveedor, int cantidadPedida){
        this.proveedor = proveedor;
        this.cantidadPedida = cantidadPedida;
    }
}
