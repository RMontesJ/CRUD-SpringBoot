package com.example.crudusuario.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.crudusuario.model.Tarea;

    public interface TareaRepository extends JpaRepository<Tarea, Long> {
    Optional<Tarea> findByTitulo(String titulo);
}

