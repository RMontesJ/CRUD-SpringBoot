package com.example.crudusuario.controller;

import com.example.crudusuario.model.Persona;
import com.example.crudusuario.repository.PersonaRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class PersonaController {

    private final PersonaRepository personaRepository;

    public PersonaController(PersonaRepository personaRepository) {
        this.personaRepository = personaRepository;
    }

    @GetMapping("/")
    public String listar(Model model) {
        model.addAttribute("personas", personaRepository.findAll());
        return "index";
    }

    @GetMapping("/crear")
    public String crearForm(Model model) {
        model.addAttribute("persona", new Persona());
        return "crear";
    }

    @PostMapping
    public String guardar(@ModelAttribute Persona persona) {
        personaRepository.save(persona);
        return "redirect:/";
    }

    @GetMapping("/editar/{id}")
    public String editarForm(@PathVariable Long id, Model model) {
        model.addAttribute("persona", personaRepository.findById(id).orElseThrow());
        return "editar";
    }

    @PostMapping("/{id}")
    public String actualizar(@PathVariable Long id, @ModelAttribute Persona persona) {
        persona.setId(id);
        personaRepository.save(persona);
        return "redirect:/";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id) {
        personaRepository.deleteById(id);
        return "redirect:/";
    }
}
