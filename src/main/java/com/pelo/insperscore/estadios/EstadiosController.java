package com.pelo.insperscore.estadios;

import com.pelo.insperscore.estadios.dto.CreateEstadioDTO;
import com.pelo.insperscore.estadios.dto.EstadioResponseDTO;
import com.pelo.insperscore.estadios.dto.UpdateEstadioDTO;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/estadios")
public class EstadiosController {

    private final EstadiosService estadiosService;

    public EstadiosController(EstadiosService estadiosService) {
        this.estadiosService = estadiosService;
    }

    @GetMapping
    public ResponseEntity<List<EstadioResponseDTO>> getAll() {
        return ResponseEntity.ok(estadiosService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EstadioResponseDTO> getById(@PathVariable Integer id) {
        try {
            EstadioResponseDTO dto = estadiosService.findById(id);
            return ResponseEntity.ok(dto);
        } catch (RuntimeException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<EstadioResponseDTO> create(@Valid @RequestBody CreateEstadioDTO dto) {
        EstadioResponseDTO created = estadiosService.create(dto);
        return ResponseEntity.created(URI.create("/api/estadios/" + created.id())).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EstadioResponseDTO> update(@PathVariable Integer id,
                                                     @Valid @RequestBody UpdateEstadioDTO dto) {
        try {
            EstadioResponseDTO updated = estadiosService.update(id, dto);
            return ResponseEntity.ok(updated);
        } catch (RuntimeException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        try {
            estadiosService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException ex) {
            return ResponseEntity.notFound().build();
        }
    }
}
