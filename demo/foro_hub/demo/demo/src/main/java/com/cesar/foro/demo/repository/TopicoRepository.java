package com.cesar.foro.demo.repository;

import com.cesar.foro.demo.entity.Topico;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.Year;

public interface TopicoRepository extends JpaRepository<Topico, Long> {

    // Filtrar por curso y año específico
    @Query("SELECT t FROM Topico t WHERE t.curso = :curso AND FUNCTION('YEAR', t.fechaCreacion) = :anio")
    Page<Topico> findByCursoAndYear(String curso, int anio, Pageable pageable);

    // Método para obtener los primeros 10 ordenados por fecha de creación
    Page<Topico> findAllByOrderByFechaCreacionAsc(Pageable pageable);
}
