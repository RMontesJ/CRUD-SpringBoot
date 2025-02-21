package com.example.crudusuario.service;

import com.example.crudusuario.model.Proyecto;
import com.example.crudusuario.repository.ProyectoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProyectoService {

    private final ProyectoRepository proyectoRepository;

    public ProyectoService(ProyectoRepository proyectoRepository) {
        this.proyectoRepository = proyectoRepository;
    }

    // Create a new Proyecto
    public Proyecto crearProyecto(Proyecto proyecto) {
        return proyectoRepository.save(proyecto);
    }

    // Retrieve all Proyectos
    public List<Proyecto> obtenerTodosLosProyectos() {
        return proyectoRepository.findAll();
    }

    // Update an existing Proyecto
    public Proyecto actualizarProyecto(Long id, Proyecto proyectoActualizado) {
        return proyectoRepository.findById(id)
            .map(proyecto -> {
                proyecto.setNombre(proyectoActualizado.getNombre());
                proyecto.setDescripcion(proyectoActualizado.getDescripcion());
                proyecto.setFechaInicio(proyectoActualizado.getFechaInicio());
                proyecto.setEstado(proyectoActualizado.getEstado());
                return proyectoRepository.save(proyecto); // Save the updated proyecto
            })
            .orElseThrow(() -> new RuntimeException("Proyecto no encontrado"));
    }

    // Delete a Proyecto by its ID
    public void eliminarProyecto(Long id) {
        if (!proyectoRepository.existsById(id)) {
            throw new RuntimeException("Proyecto no encontrado");
        }
        proyectoRepository.deleteById(id);
    }

    // Find a Proyecto by its ID
    public Optional<Proyecto> obtenerProyectoPorId(Long id) {
        return proyectoRepository.findById(id);
    }
}
