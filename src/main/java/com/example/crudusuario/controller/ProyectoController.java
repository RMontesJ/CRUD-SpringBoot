package com.example.crudusuario.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.crudusuario.model.Proyecto;
import com.example.crudusuario.repository.ProyectoRepository;
import com.example.crudusuario.service.ProyectoService;

@Controller
@RequestMapping("/proyectos")
public class ProyectoController {

    private final ProyectoRepository proyectoRepository;
    private ProyectoService proyectoService;

    public ProyectoController(ProyectoRepository proyectoRepository) {
        this.proyectoRepository = proyectoRepository;
    }

    @GetMapping("/")
    public String listar(Model model) {
        model.addAttribute("proyectos", proyectoRepository.findAll());
        return "index";
    }
 
    @GetMapping("/crear")
    public String crearForm(Model model) {
        model.addAttribute("proyecto", new Proyecto());
        return "proyecto/crear";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Proyecto proyecto) {
        proyectoRepository.save(proyecto);
        return "redirect:/proyectos/";
    }

    @GetMapping("/editar/{id}")
    public String editarForm(@PathVariable Long id, Model model) {
        model.addAttribute("proyecto", proyectoRepository.findById(id).orElseThrow());
        return "proyecto/editar";
    }

     @PostMapping("/actualizar/{id}")
    public String actualizarProyecto(@PathVariable Long id, @ModelAttribute Proyecto proyecto) {
        proyectoService.actualizarProyecto(id, proyecto);
        return "redirect:/usuarios/listar";
    }



    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id) {
        proyectoRepository.deleteById(id);
        return "redirect:/proyectos/";
    }
}
