package com.example.crudusuario.service;

import com.example.crudusuario.repository.TareaRepository;
import com.example.crudusuario.model.Tarea;
import com.example.crudusuario.model.Usuario;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TareaService {

    private final TareaRepository tareaRepository;

    public TareaService(TareaRepository tareaRepository) {
        this.tareaRepository = tareaRepository;
    }

    // Method to create a new Tarea
    public Tarea registrarTarea(Tarea tarea) {
        return tareaRepository.save(tarea);
    }

    // Method to retrieve all Tareas
    public List<Tarea> obtenerTodasLasTareas() {
        return tareaRepository.findAll();
    }

    // Method to update an existing Tarea
    public Tarea actualizarTarea(Long id, Tarea tarea) {
        return tareaRepository.findById(id)
            .map(tareas -> {
                tarea.setTitulo(tarea.getTitulo());
                tarea.setDescripcion(tarea.getDescripcion());
                tarea.setFechaLimite(tarea.getFechaLimite());
                tarea.setEstado(tarea.getEstado());
                tarea.setUsuario(tarea.getUsuario());
                return tareaRepository.save(tarea); // Save the updated tarea
            })
            .orElseThrow(() -> new RuntimeException("Tarea no encontrada"));
    }

    // Method to delete a Tarea by its ID
    public void eliminarTarea(Long id) {
        if (!tareaRepository.existsById(id)) {
            throw new RuntimeException("Tarea no encontrada");
        }
        tareaRepository.deleteById(id);
    }

    // Method to find a Tarea by its ID
    public Tarea obtenerTareaPorId(Long id) {
        return tareaRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Usuario no encontrado con el id: " + id));
    }
}
