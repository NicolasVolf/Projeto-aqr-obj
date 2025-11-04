package com.pelo.insperscore.partidas;

import com.pelo.insperscore.partidas.dto.CreatePartidaDTO;
import com.pelo.insperscore.partidas.dto.PartidaResponseDTO;
import com.pelo.insperscore.partidas.dto.UpdatePartidaDTO;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/partidas")
public class PartidasController {

    private final PartidasService partidasService;

    public PartidasController(PartidasService partidasService) {
        this.partidasService = partidasService;
    }

    @GetMapping
    public ResponseEntity<List<PartidaResponseDTO>> getAll() {
        return ResponseEntity.ok(partidasService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PartidaResponseDTO> getById(@PathVariable Integer id) {
        try {
            PartidaResponseDTO dto = partidasService.findById(id);
            return ResponseEntity.ok(dto);
        } catch (RuntimeException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<PartidaResponseDTO> create(@Valid @RequestBody CreatePartidaDTO dto) {
        PartidaResponseDTO created = partidasService.create(dto);
        return ResponseEntity.created(URI.create("/api/partidas/" + created.id())).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PartidaResponseDTO> update(@PathVariable Integer id,
                                                     @Valid @RequestBody UpdatePartidaDTO dto) {
        try {
            PartidaResponseDTO updated = partidasService.update(id, dto);
            return ResponseEntity.ok(updated);
        } catch (RuntimeException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        try {
            partidasService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException ex) {
            return ResponseEntity.notFound().build();
        }
    }
}
