package com.cesar.foro.demo.dto;

import jakarta.validation.constraints.NotBlank;

public record TopicoRequestDTO(
        @NotBlank(message = "El título no puede estar vacío") String titulo,
        @NotBlank(message = "La descripción no puede estar vacía") String descripcion,
        @NotBlank(message = "El autor no puede estar vacío") String autor,
        @NotBlank(message = "El curso no puede estar vacío") String curso,
        boolean activo
) {}
