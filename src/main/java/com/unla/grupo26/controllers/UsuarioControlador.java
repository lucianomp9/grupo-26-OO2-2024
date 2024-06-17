package com.unla.grupo26.controllers;

import com.unla.grupo26.dto.UsuarioDTO;
import com.unla.grupo26.services.Impl.RolUsuarioServicio;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.unla.grupo26.entities.Usuario;
import com.unla.grupo26.services.Impl.UsuarioServicio;

@Controller
@RequestMapping("/usuarios")
public class UsuarioControlador {

    private UsuarioServicio usuarioServicio;
    private RolUsuarioServicio rolUsuarioServicio;

    public UsuarioControlador(UsuarioServicio usuarioServicio, RolUsuarioServicio rolUsuarioServicio){
        this.usuarioServicio = usuarioServicio;
        this.rolUsuarioServicio = rolUsuarioServicio;
    }


    @GetMapping("/login")
    public String mostrarFormularioLogin(Model model,
                                         @RequestParam(name="error", required=false) String error,
                                         @RequestParam(name="logout", required=false) String logout) {
        model.addAttribute("error", error);
        model.addAttribute("logout", logout);
        return "login"; // nombre de la vista para el formulario de login
    }

    @GetMapping("/logout")
    public String logout(Model model) {
        return "logout"; // nombre de la vista para el logout
    }

    @GetMapping("/registro")
    public String mostrarFormularioRegistro(Model model) {
        model.addAttribute("usuarioDTO", new UsuarioDTO());
        model.addAttribute("roles", rolUsuarioServicio.obtenerTodosRolUsuarios()); // obtener roles disponibles
        return "registro"; // nombre de la vista para el formulario de registro
    }

    @PostMapping("/registro")
    public String registrarUsuario(@ModelAttribute("usuario") UsuarioDTO usuarioDTO) throws Exception {
        // Validar y guardar el nuevo usuario en la base de datos
        usuarioServicio.registrarUsuario(usuarioDTO);
        return "redirect:/usuarios/login"; // redireccionar al formulario de login
    }

    @GetMapping("/perfil/{id}")
    public String mostrarPerfilUsuario(@PathVariable("id") int id, Model model) {
        Usuario usuario = usuarioServicio.obtenerPorId(id);
        if (usuario != null) {
            model.addAttribute("usuario", usuario);
            return "perfil"; // nombre de la vista para mostrar el perfil del usuario
        } else {
            return "redirect:/usuarios/login"; // redireccionar al login si no se encuentra el usuario
        }
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEdicion(@PathVariable("id") int id, Model model) {
        Usuario usuario = usuarioServicio.obtenerPorId(id);
        if (usuario != null) {
            model.addAttribute("usuario", usuario);
            model.addAttribute("roles", rolUsuarioServicio.obtenerTodosRolUsuarios()); // obtener roles disponibles
            return "edicion"; // nombre de la vista para el formulario de edición de usuario
        } else {
            return "redirect:/usuarios/login"; // redireccionar al login si no se encuentra el usuario
        }
    }

    @PostMapping("/editar/{id}")
    public String editarUsuario(@PathVariable("id") int id, @ModelAttribute("usuarioDTO") UsuarioDTO usuarioDTO) throws Exception {
        // Validar y actualizar los datos del usuario en la base de datos
        usuarioServicio.actualizarUsuario(usuarioDTO);
        return "redirect:/usuarios/perfil/" + id; // redireccionar al perfil del usuario editado
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarUsuario(@PathVariable("id") int id) {
        usuarioServicio.eliminar(id);
        return "redirect:/usuarios/logout"; // redireccionar al logout después de eliminar el usuario
    }
}