package com.unla.grupo26.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UsuarioDTO {

    private String usuario;
    private String password;
    private String nombre;
    private String apellido;
    private int dni;
}
