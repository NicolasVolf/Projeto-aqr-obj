package com.pelo.insperscore.campeonatos;

import com.pelo.insperscore.campeonatos.dto.CampeonatoResponseDTO;
import com.pelo.insperscore.campeonatos.dto.CreateCampeonatoDTO;
import com.pelo.insperscore.campeonatos.dto.UpdateCampeonatoDTO;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/campeonatos")
public class CampeonatosController {

    private final CampeonatosService campeonatosService;

    public CampeonatosController(CampeonatosService campeonatosService) {
        this.campeonatosService = campeonatosService;
    }

    @GetMapping
    public ResponseEntity<List<CampeonatoResponseDTO>> getAll() {
        return ResponseEntity.ok(campeonatosService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CampeonatoResponseDTO> getById(@PathVariable Integer id) {
        CampeonatoResponseDTO dto = campeonatosService.getPorId(id);
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<CampeonatoResponseDTO> create(@Valid @RequestBody CreateCampeonatoDTO dto) {
        CampeonatoResponseDTO created = campeonatosService.create(dto);
        return ResponseEntity.created(URI.create("/api/campeonatos/" + created.id())).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CampeonatoResponseDTO> update(@PathVariable Integer id,
                                                        @Valid @RequestBody UpdateCampeonatoDTO dto) {
        CampeonatoResponseDTO updated = campeonatosService.update(id, dto);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        campeonatosService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
