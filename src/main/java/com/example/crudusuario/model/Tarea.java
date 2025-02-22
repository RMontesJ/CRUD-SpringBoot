package com.example.crudusuario.model;

import java.sql.Date;

import jakarta.persistence.*;

@Entity
@Table(name = "tareas")
public class Tarea {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String descripcion;
    private Date fechaLimite;

    @Enumerated(EnumType.STRING)
    private EstadoTarea estado;

    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;

    // Nuevo campo para almacenar el ID del usuario directamente
    @Column(name = "id_usuario", insertable = false, updatable = false)
    private Long idUsuario;

    public Tarea() {}

    public Tarea(String titulo, String descripcion, Date fechaLimite, EstadoTarea estado, Usuario usuario) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.fechaLimite = fechaLimite;
        this.estado = estado;
        this.usuario = usuario;
        this.idUsuario = usuario != null ? usuario.getId() : null; // Asignar idUsuario a partir del usuario
    }

    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public Date getFechaLimite() {
        return fechaLimite;
    }

    public EstadoTarea getEstado() {
        return estado;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setFechaLimite(Date fechaLimite) {
        this.fechaLimite = fechaLimite;
    }

    public void setEstado(EstadoTarea estado) {
        this.estado = estado;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
        this.idUsuario = usuario != null ? usuario.getId() : null; // Actualizar el idUsuario cuando se cambia el usuario
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }
}

enum EstadoTarea {
    PENDIENTE,
    EN_CURSO,
    COMPLETADA
}
