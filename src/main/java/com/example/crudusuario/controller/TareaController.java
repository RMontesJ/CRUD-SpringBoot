package com.example.crudusuario.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.crudusuario.model.Tarea;
import com.example.crudusuario.service.TareaService;

@Controller
public class TareaController {

	private final TareaService tareaService;

	public TareaController(TareaService tareaService) {
		this.tareaService = tareaService;
	}

	@GetMapping("/user/home")
	public String volverAlMenu(Model model) {
		model.addAttribute("tarea", new Tarea()); // Crear un objeto usuario vacío
		return "/user/home"; // Esta es la vista donde el formulario de creación se mostrará
	}

	@GetMapping("/tarea/crear")
	public String mostrarFormularioCreacion(Model model) {
		model.addAttribute("tarea", new Tarea()); // Crear un objeto usuario vacío
		return "/tarea/crear"; // Esta es la vista donde el formulario de creación se mostrará
	}

	@PostMapping("/tarea/crear")
	public String registrarTarea(@ModelAttribute Tarea tarea) {
		tareaService.registrarTarea(tarea);
		return "redirect:/tarea/listar"; // Redirige a la lista de usuarios después de crear el nuevo
	}

	@GetMapping("/tarea/editar/{id}")
	public String mostrarFormularioEdicion(@PathVariable Long id, Model model) {
		Tarea tarea = tareaService.obtenerTareaPorId(id);
		model.addAttribute("tarea", tarea);
		return "/tarea/editar"; // Esta es la vista donde el formulario de edición se mostrará
	}

	@PostMapping("/tarea/editar/{id}")
	public String actualizarTarea(@PathVariable Long id, @ModelAttribute Tarea tarea) {
		tareaService.actualizarTarea(id, tarea);
		return "/tarea/listar";
	}

	@GetMapping("/tarea/listar")
	public String listarTareas(Model model) {
		List<Tarea> tareas = tareaService.obtenerTodasLasTareas();
		model.addAttribute("tareas", tareas);
		return "tarea/listar";
	}

	@GetMapping("tarea/eliminar/{id}")
	public String eliminarTarea(@PathVariable Long id) {
		tareaService.eliminarTarea(id);
		return "redirect:/tarea/listar";
	}
}
