package com.cesar.foro.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.cesar.foro.demo.dto.TopicoRequestDTO;
import com.cesar.foro.demo.dto.TopicoResponseDTO;
import com.cesar.foro.demo.service.TopicoService;

import java.util.List;

@RestController
@RequestMapping("/api/topicos")
public class TopicoController {

    private final TopicoService service;
    private static final Logger log = LoggerFactory.getLogger(TopicoController.class);

    // Inyección del servicio
    public TopicoController(TopicoService service) {
        this.service = service;
    }

    // Endpoint para crear un tópico
    @PostMapping
    public ResponseEntity<?> crearTopico(@RequestBody TopicoRequestDTO dto) {
        try {
            log.info("Creando tópico con título: {}, autor: {}", dto.titulo(), dto.autor());
            TopicoResponseDTO topicoCreado = service.crearTopico(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(topicoCreado);
        } catch (Exception e) {
            log.error("Error al crear el tópico", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al crear el tópico");
        }
    }


    // ✅ Endpoint para listar todos los tópicos
    @GetMapping
    public ResponseEntity<List<TopicoResponseDTO>> listarTopicos() {
        try {
            List<TopicoResponseDTO> topicos = service.listarTopicos();
            log.info("Listando tópicos, cantidad: {}", topicos.size());
            return ResponseEntity.ok(topicos);
        } catch (Exception e) {
            log.error("Error al listar los tópicos", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    // ✅ Endpoint para listar los primeros 10 tópicos ordenados por fecha de creación
    @GetMapping("/ordenados")
    public ResponseEntity<List<TopicoResponseDTO>> listarTopicosOrdenadosPorFecha() {
        try {
            List<TopicoResponseDTO> topicos = service.listarTopicosOrdenadosPorFecha();
            log.info("Listando los primeros 10 tópicos ordenados por fecha de creación.");
            return ResponseEntity.ok(topicos);
        } catch (Exception e) {
            log.error("Error al listar los tópicos ordenados", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    // ✅ Endpoint para filtrar tópicos por curso y año
    @GetMapping("/filtro")
    public ResponseEntity<List<TopicoResponseDTO>> listarTopicosPorCursoYAno(
            @RequestParam String curso,
            @RequestParam int ano) {
        try {
            List<TopicoResponseDTO> topicos = service.listarTopicosPorCursoYAno(curso, ano);
            log.info("Listando tópicos para el curso: {}, año: {}", curso, ano);
            return ResponseEntity.ok(topicos);
        } catch (Exception e) {
            log.error("Error al filtrar los tópicos", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    // Endpoint para obtener detalles de un tópico por ID
    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerTopicoPorId(@PathVariable Long id) {
        try {
            TopicoResponseDTO topico = service.obtenerTopicoPorId(id);

            if (topico != null) {
                // Accede a los campos directamente sin métodos getters
                log.info("Obteniendo detalles del tópico con ID: {}, título: {}, autor: {}",
                        id, topico.titulo(), topico.autor());

                return ResponseEntity.ok(topico);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Tópico no encontrado con ID: " + id);
            }
        } catch (Exception e) {
            log.error("Error al obtener el tópico con ID: " + id, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al obtener el tópico");
        }
    }



    // Endpoint para actualizar un tópico por ID
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarTopico(@PathVariable Long id, @RequestBody TopicoRequestDTO dto) {
        try {
            log.info("Actualizando tópico con ID: {}", id);

            ResponseEntity<?> topicoActualizado = service.actualizarTopico(id, dto);
            if (topicoActualizado != null) {
                return ResponseEntity.ok(topicoActualizado);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Tópico no encontrado con ID: " + id);
            }
        } catch (Exception e) {
            log.error("Error al actualizar el tópico con ID: " + id, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al actualizar el tópico");
        }
        // Endpoint para eliminar un tópico
        @DeleteMapping("/{id}")
        public ResponseEntity<Void> eliminarTopico(@PathVariable Long id) {
            boolean eliminado = service.eliminarTopico(id);

            if (eliminado) {
                return ResponseEntity.noContent().build(); // Código 204
            } else {
                return ResponseEntity.notFound().build(); // Código 404

    }



}
