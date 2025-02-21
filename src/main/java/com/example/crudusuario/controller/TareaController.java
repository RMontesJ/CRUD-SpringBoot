package com.example.crudusuario.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.example.crudusuario.model.Tarea;
import com.example.crudusuario.repository.TareaRepository;

@Controller
@RequestMapping("/tareas")
public class TareaController {

    private final TareaRepository tareaRepository;

    public TareaController(TareaRepository tareaRepository) {
        this.tareaRepository = tareaRepository;
    }

    @GetMapping("/")
    public String listar(Model model) {
        model.addAttribute("tareas", tareaRepository.findAll());
        return "index";
    }

    @GetMapping("/crear")
    public String crearForm(Model model) {
        model.addAttribute("tarea", new Tarea());
        return "tarea/crear";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Tarea tarea) {
        tareaRepository.save(tarea);
        return "redirect:/tareas/";
    }

    @GetMapping("/editar/{id}")
    public String editarForm(@PathVariable Long id, Model model) {
        model.addAttribute("tarea", tareaRepository.findById(id).orElseThrow());
        return "tarea/editar";
    }

    @PostMapping("/actualizar/{id}")
    public String actualizar(@PathVariable Long id, @ModelAttribute Tarea tarea) {
        tarea.setId(id);
        tareaRepository.save(tarea);
        return "redirect:/tareas/";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id) {
        tareaRepository.deleteById(id);
        return "redirect:/tareas/";
    }
}
