package com.unla.grupo26.services.Impl;

import com.unla.grupo26.entities.RolUsuario;
import com.unla.grupo26.repositories.IRolUsuarioRepositorio;
import com.unla.grupo26.services.IRolUsuario;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RolUsuarioServicio implements IRolUsuario {

    private IRolUsuarioRepositorio rolUsuarioRepositorio;


    @Override
    public RolUsuario guardarOActualizarRolUsuario(RolUsuario rolUsuario) {
        return rolUsuarioRepositorio.save(rolUsuario);
    }

    @Override
    public List<RolUsuario> obtenerTodosRolUsuarios(){
        return rolUsuarioRepositorio.findAll();
    }

    @Override
    public RolUsuario obtenerRolUsuarioPorId(long id){
        return rolUsuarioRepositorio.findById(id).orElse(null);
    }
}
