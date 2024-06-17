package com.unla.grupo26.dto;


import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;



@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CrearLoteDTO {

	@NotNull(message = "La fecha de recepción no puede ser nula")
    private LocalDate fechaRecepcion;

    @PositiveOrZero(message = "La cantidad recibida debe ser un número positivo o cero")
    private int cantidadRecibida;

    @PositiveOrZero(message = "El precio del lote debe ser un número positivo o cero")
    private float precioLote;

    @NotBlank(message = "El proveedor no puede estar en blanco")
    private String proveedor;

    @PositiveOrZero(message = "El ID del almacén debe ser un número positivo o cero")
    private long idAlmacen;
    
	public long getIdAlmacen() {
		return idAlmacen;
	}
    
	
    
    
}
