package com.unla.grupo26.services.Impl;

import com.unla.grupo26.dto.UsuarioDTO;
import com.unla.grupo26.mappers.UsuarioMapper;
import com.unla.grupo26.mappers.UsuarioMapperImpl;
import com.unla.grupo26.services.IUsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.unla.grupo26.entities.RolUsuario;
import com.unla.grupo26.entities.Usuario;
import com.unla.grupo26.repositories.IRolUsuarioRepositorio;
import com.unla.grupo26.repositories.IUsuarioRepositorio;

import javax.swing.text.html.Option;
import java.util.*;

@Service
public class UsuarioServicio implements IUsuarioServicio, UserDetailsService {

    private IUsuarioRepositorio usuarioRepositorio;
    private UsuarioMapper usuarioMapper;
    private PasswordEncoder passwordEncoder;

    public UsuarioServicio(IUsuarioRepositorio usuarioRepositorio, UsuarioMapper usuarioMapper, PasswordEncoder passwordEncoder){
        this.usuarioRepositorio = usuarioRepositorio;
        this.usuarioMapper = usuarioMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Usuario> obtenerUsuarios() {
        return usuarioRepositorio.findAll();
    }

    @Override
    public Usuario obtenerUsuarioPorId(long id) throws Exception {
        Optional<Usuario> usuarioOptional = usuarioRepositorio.findById(id);
        if (usuarioOptional.isEmpty()) {
            throw new Exception("El usuario no ha sido encontrado");
        }
        return usuarioOptional.get();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Usuario> usuarioOptional = usuarioRepositorio.findByUsuario(username);

        if(usuarioOptional.isEmpty()){
            throw new UsernameNotFoundException(username);
        }
        return buildUser(usuarioOptional.get());
    }


    private User buildUser(Usuario usuario) {
        return new User(usuario.getUsuario(), usuario.getPassword(), buildGrantedAuthorities(usuario.getRolUsuario()));
    }

    private List<GrantedAuthority> buildGrantedAuthorities(RolUsuario rol) {
        return Arrays.asList(new SimpleGrantedAuthority(rol.getRol()));
    }

    @Transactional
    public Usuario registrarUsuario(UsuarioDTO usuarioDTO) throws Exception {

        Optional<Usuario> optionalUsuario = usuarioRepositorio.findByUsuario(usuarioDTO.getUsuario());

        if(optionalUsuario.isPresent()){
            throw new Exception("Ya existe un usuario con ese nombre"); // TODO: Crear clases de excepciones personalizadas
        }

        Usuario usuario = usuarioMapper.usuarioDTOAUsuario(usuarioDTO);

        usuario.setPassword(passwordEncoder.encode(usuario.getPassword())); // Clave cifrada

        return usuarioRepositorio.save(usuario);
    }

    @Transactional
    public UsuarioDTO actualizarUsuario(UsuarioDTO usuarioDTO) throws Exception {
        Optional<Usuario> optionalUsuario = usuarioRepositorio.findByUsuario(usuarioDTO.getUsuario());

        if(optionalUsuario.isEmpty()){
            throw new Exception("El usuario a modificar no existe"); // TODO: Crear clases de excepciones personalizadas
        }

        Usuario usuario = optionalUsuario.get();


        usuario.setNombre(usuarioDTO.getNombre());
        usuario.setApellido(usuarioDTO.getApellido());

        if(usuarioDTO.getDni() != usuario.getDni()){
            Optional<Usuario> optionalUsuario2 = usuarioRepositorio.findByDni(usuarioDTO.getDni());

            if(optionalUsuario2.isPresent()){
                throw new Exception("Ya existe un usuario con ese DNI"); // Si existe un usuario con el DNI al que se quiere modificar se tira excepcion
            }

            usuario.setDni(usuarioDTO.getDni());
        }

        if(usuarioDTO.getPassword() != null){
            usuarioDTO.setPassword(passwordEncoder.encode(usuarioDTO.getPassword())); // Si una nueva contraseña esta presente en los datos del usuario, la actualizamos.
        }

        return usuarioMapper.usuarioAUsuarioDTO(usuarioRepositorio.save(usuario));
    }

    @Transactional
    public void eliminar(long id) {
        usuarioRepositorio.deleteById(id);
    }

    @Transactional(readOnly = true)
    public Usuario obtenerPorId(long id) {
        return usuarioRepositorio.findById(id).orElse(null);
    }

    @Transactional(readOnly = true)
    public Usuario buscarPorNombre(String nombre) {
        return usuarioRepositorio.findByNombre(nombre);
    }

}
