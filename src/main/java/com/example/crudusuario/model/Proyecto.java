package com.example.crudusuario.model;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "proyectos")
public class Proyecto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String descripcion;
    private Date fechaInicio;

    @Enumerated(EnumType.STRING)
    private EstadoProyecto estado;

    public Proyecto() {}

    public Proyecto(String nombre, String descripcion, Date fechaInicio, EstadoProyecto estado) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.fechaInicio = fechaInicio;
        this.estado = estado;
    }

    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public EstadoProyecto getEstado() {
        return estado;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public void setEstado(EstadoProyecto estado) {
        this.estado = estado;
    }
}

enum EstadoProyecto {
    ACTIVO,
    EN_PROGRESO,
    FINALIZADO
}
