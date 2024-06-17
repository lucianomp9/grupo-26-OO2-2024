package com.unla.grupo26.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.unla.grupo26.entities.RolUsuario;
import com.unla.grupo26.entities.Usuario;
import com.unla.grupo26.repositories.IRolUsuarioRepositorio;
import com.unla.grupo26.repositories.IUsuarioRepositorio;

import java.util.List;

@Service
@Transactional
public class UsuarioServicio {

    @Autowired
    private IUsuarioRepositorio usuarioRepositorio;

    @Autowired
    private IRolUsuarioRepositorio rolUsuarioRepositorio;

    public Usuario guardarOActualizar(Usuario usuario) {
        return usuarioRepositorio.save(usuario);
    }

    public void eliminar(int id) {
        usuarioRepositorio.deleteById(id);
    }

    public Usuario obtenerPorId(int id) {
        return usuarioRepositorio.findById(id).orElse(null);
    }

    public Usuario buscarPorNombre(String nombre) {
        return usuarioRepositorio.findByNombre(nombre);
    }

    public List<Usuario> obtenerTodos() {
        return usuarioRepositorio.findAll();
    }

    public RolUsuario guardarOActualizarRolUsuario(RolUsuario rolUsuario) {
        return rolUsuarioRepositorio.save(rolUsuario);
    }

    public RolUsuario obtenerRolUsuarioPorId(int id) {
        return rolUsuarioRepositorio.findById(id).orElse(null);
    }

    public List<RolUsuario> obtenerTodosRolUsuarios() {
        return rolUsuarioRepositorio.findAll();
    }
}
