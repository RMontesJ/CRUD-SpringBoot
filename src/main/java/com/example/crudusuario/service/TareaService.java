package com.example.crudusuario.service;

import com.example.crudusuario.repository.TareaRepository;
import com.example.crudusuario.model.Tarea;
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
    public Tarea crearTarea(Tarea tarea) {
        return tareaRepository.save(tarea);
    }

    // Method to retrieve all Tareas
    public List<Tarea> obtenerTodasLasTareas() {
        return tareaRepository.findAll();
    }

    // Method to update an existing Tarea
    public Tarea actualizarTarea(Long id, Tarea tareaActualizada) {
        return tareaRepository.findById(id)
            .map(tarea -> {
                tarea.setTitulo(tareaActualizada.getTitulo());
                tarea.setDescripcion(tareaActualizada.getDescripcion());
                tarea.setFechaLimite(tareaActualizada.getFechaLimite());
                tarea.setEstado(tareaActualizada.getEstado());
                tarea.setUsuario(tareaActualizada.getUsuario());
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
    public Optional<Tarea> obtenerTareaPorId(Long id) {
        return tareaRepository.findById(id);
    }
}
