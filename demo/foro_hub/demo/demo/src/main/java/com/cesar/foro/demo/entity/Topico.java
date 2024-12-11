package com.cesar.foro.demo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "topicos")
public class Topico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El título no puede estar vacío")
    @Column(nullable = false)
    private String titulo;

    @NotBlank(message = "La descripción no puede estar vacía")
    @Column(nullable = false)
    private String descripcion;

    @NotBlank(message = "El autor no puede estar vacío")
    @Column(nullable = false)
    private String autor;

    @NotBlank(message = "El curso no puede estar vacío")
    @Column(nullable = false)
    private String curso;

    @Column(nullable = false, updatable = false)
    private LocalDate fechaCreacion;

    @Column(nullable = false)
    private boolean activo;

    @PrePersist
    protected void onCreate() {
        this.fechaCreacion = LocalDate.now();
    }
}
