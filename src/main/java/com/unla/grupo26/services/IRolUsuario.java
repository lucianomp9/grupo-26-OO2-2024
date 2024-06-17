package com.unla.grupo26.services;

import com.unla.grupo26.entities.RolUsuario;

import java.util.List;

public interface IRolUsuario {

    public RolUsuario guardarOActualizarRolUsuario(RolUsuario rolUsuario);

    public RolUsuario obtenerRolUsuarioPorId(long id);

    public List<RolUsuario> obtenerTodosRolUsuarios();

}
