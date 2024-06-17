package com.unla.grupo26.mappers;

import com.unla.grupo26.dto.UsuarioDTO;
import com.unla.grupo26.entities.Usuario;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UsuarioMapper {
        UsuarioDTO usuarioAUsuarioDTO(Usuario usuario);
        Usuario usuarioDTOAUsuario(UsuarioDTO usuarioDTO);
        List<UsuarioDTO> listaUsuarioAListaUsuarioDTO(List<Usuario> usuarioList);
    }
