package com.cesar.foro.demo.dto;

import java.time.LocalDate;

public record TopicoResponseDTO(
        Long id,
        String titulo,
        String descripcion,
        boolean activo,
        String autor,
        String curso,
        LocalDate fechaCreacion
) {}
