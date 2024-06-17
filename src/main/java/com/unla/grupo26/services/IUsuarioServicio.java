package com.unla.grupo26.services;

import com.unla.grupo26.dto.UsuarioDTO;
import com.unla.grupo26.entities.Usuario;

import java.util.List;

public interface IUsuarioServicio {

    public List<Usuario> obtenerUsuarios();

    public Usuario obtenerUsuarioPorId(long id) throws Exception;
}