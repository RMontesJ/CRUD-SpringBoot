package com.example.crudusuario.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.example.crudusuario.model.Usuario;
import com.example.crudusuario.service.UsuarioService;
import java.util.List;

@Controller
public class UsuarioController {
    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping("/user/crear")
public String mostrarFormularioCreacion(Model model) {
    model.addAttribute("usuario", new Usuario());  // Crear un objeto usuario vacío
    return "/user/crear"; // Esta es la vista donde el formulario de creación se mostrará
}

@PostMapping("/user/crear")
public String registrarUsuario(@ModelAttribute Usuario usuario) {
    usuarioService.registrarUsuario(usuario);
    return "redirect:/user/listar";  // Redirige a la lista de usuarios después de crear el nuevo
}

@GetMapping("/user/editar/{id}")
public String mostrarFormularioEdicion(@PathVariable Long id, Model model) {
    Usuario usuario = usuarioService.obtenerUsuarioPorId(id);
    model.addAttribute("usuario", usuario);
    return "user/editar"; // Esta es la vista donde el formulario de edición se mostrará
}

@PostMapping("/user/editar/{id}")
    public String actualizarUsuario(@PathVariable Long id, @ModelAttribute Usuario usuario) {
        usuarioService.actualizarUsuario(id, usuario);
        return "redirect:/user/listar";
    }


    @GetMapping("/user/listar")
    public String listarUsuarios(Model model) {
        List<Usuario> usuarios = usuarioService.obtenerTodosLosUsuarios();
        model.addAttribute("usuarios", usuarios);
        return "user/listar";
    }

    @GetMapping("user/eliminar/{id}")
    public String eliminarUsuario(@PathVariable Long id) {
        usuarioService.eliminarUsuario(id);
        return "redirect:/user/listar";
    }

    @GetMapping("/home")
    public String home(Model model) {
        return "user/home";
    }
}
