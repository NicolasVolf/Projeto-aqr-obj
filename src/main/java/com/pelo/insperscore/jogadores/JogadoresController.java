package com.pelo.insperscore.jogadores;

import com.pelo.insperscore.jogadores.dto.CreateJogadorDTO;
import com.pelo.insperscore.jogadores.dto.JogadorResponseDTO;
import com.pelo.insperscore.jogadores.dto.UpdateJogadorDTO;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/jogadores")
public class JogadoresController {

    private final JogadoresService jogadoresService;

    public JogadoresController(JogadoresService jogadoresService) {
        this.jogadoresService = jogadoresService;
    }

    @GetMapping
    public ResponseEntity<List<JogadorResponseDTO>> getAll() {
        return ResponseEntity.ok(jogadoresService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<JogadorResponseDTO> getById(@PathVariable Integer id) {
        JogadorResponseDTO dto = jogadoresService.getPorId(id);
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<JogadorResponseDTO> create(@Valid @RequestBody CreateJogadorDTO dto) {
        JogadorResponseDTO created = jogadoresService.create(dto);
        return ResponseEntity.created(URI.create("/api/jogadores/" + created.id())).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<JogadorResponseDTO> update(@PathVariable Integer id,
                                                     @Valid @RequestBody UpdateJogadorDTO dto) {
        JogadorResponseDTO updated = jogadoresService.update(id, dto);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        jogadoresService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
