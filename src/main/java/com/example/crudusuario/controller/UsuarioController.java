package com.example.crudusuario.controller;

import com.example.crudusuario.model.Usuario;
import com.example.crudusuario.repository.UsuarioRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class UsuarioController {

    private final UsuarioRepository usuarioRepository;

    public UsuarioController(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @GetMapping("/")
    public String listar(Model model) {
        model.addAttribute("personas", usuarioRepository.findAll());
        return "index";
    }

    @GetMapping("/user/crear")
    public String crearForm(Model model) {
        model.addAttribute("persona", new Usuario());
        return "/usuario/crear";
    }

    @PostMapping
    public String guardar(@ModelAttribute Usuario persona) {
    	usuarioRepository.save(persona);
        return "redirect:/";
    }

    @GetMapping("/editar/{id}")
    public String editarForm(@PathVariable Long id, Model model) {
        model.addAttribute("persona", usuarioRepository.findById(id).orElseThrow());
        return "editar";
    }

    @PostMapping("/{id}")
    public String actualizar(@PathVariable Long id, @ModelAttribute Usuario persona) {
        persona.setId(id);
        usuarioRepository.save(persona);
        return "redirect:/";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id) {
    	usuarioRepository.deleteById(id);
        return "redirect:/";
    }
}
