package com.cesar.foro.demo.service;

import com.cesar.foro.demo.dto.TopicoRequestDTO;
import com.cesar.foro.demo.dto.TopicoResponseDTO;
import com.cesar.foro.demo.entity.Topico;
import com.cesar.foro.demo.repository.TopicoRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TopicoService {

    private final TopicoRepository repository;

    // Inyección del repositorio
    public TopicoService(TopicoRepository repository) {
        this.repository = repository;
    }

    // ✅ Crear un nuevo tópico
    public TopicoResponseDTO crearTopico(TopicoRequestDTO dto) {
        Topico topico = new Topico();
        topico.setTitulo(dto.titulo());
        topico.setDescripcion(dto.descripcion());
        topico.setActivo(dto.activo());
        topico.setAutor(dto.autor());
        topico.setCurso(dto.curso());
        topico.setFechaCreacion(LocalDate.now());
        topico = repository.save(topico);

        return convertirADto(topico);
    }

    // ✅ Listar todos los tópicos
    public List<TopicoResponseDTO> listarTopicos() {
        return repository.findAll().stream()
                .map(this::convertirADto)
                .collect(Collectors.toList());
    }

    // ✅ Listar los primeros 10 tópicos ordenados por fecha de creación (ASC)
    public List<TopicoResponseDTO> listarTopicosOrdenadosPorFecha() {
        return repository.findAllByOrderByFechaCreacionAsc(Pageable.ofSize(10)).stream()
                .map(this::convertirADto)
                .collect(Collectors.toList());
    }

    // ✅ Listar tópicos por curso y año específico
    public List<TopicoResponseDTO> listarTopicosPorCursoYAno(String curso, int anio) {
        return repository.findByCursoAndYear(curso, anio, Pageable.unpaged()).stream()
                .map(this::convertirADto)
                .collect(Collectors.toList());
    }

    // ✅ Obtener detalles de un tópico específico por su ID
    public TopicoResponseDTO obtenerTopicoPorId(Long id) {
        Optional<Topico> topicoOpt = repository.findById(id);
        return topicoOpt.map(this::convertirADto).orElse(null);
    }

    // 📜 Método auxiliar para convertir la entidad a DTO
    private TopicoResponseDTO convertirADto(Topico topico) {
        return new TopicoResponseDTO(
                topico.getId(),
                topico.getTitulo(),
                topico.getDescripcion(),
                topico.isActivo(),
                topico.getAutor(),
                topico.getCurso(),
                topico.getFechaCreacion()
        );
    }

    // ✅ Actualizar un tópico por ID (Método PUT)
    public ResponseEntity<?> actualizarTopico(Long id, TopicoRequestDTO dto) {
        Optional<Topico> topicoOpt = repository.findById(id);

        if (topicoOpt.isPresent()) {
            Topico topico = topicoOpt.get();

            // Actualizando datos del tópico
            topico.setTitulo(dto.titulo());
            topico.setDescripcion(dto.descripcion());
            topico.setActivo(dto.activo());
            topico.setAutor(dto.autor());
            topico.setCurso(dto.curso());

            topico = repository.save(topico);

            return ResponseEntity.ok(convertirADto(topico));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Tópico no encontrado con ID: " + id);
        }
    }

    // ✅ Eliminar un tópico por ID
    public boolean eliminarTopico(Long id) {
        Optional<Topico> topicoOpt = repository.findById(id);

        if (topicoOpt.isPresent()) {
            repository.deleteById(id); // Elimina el tópico si existe
            return true; // Indica que se eliminó correctamente
        } else {
            return false; // Indica que no se encontró el tópico
        }
    }
}
