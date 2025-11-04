package com.pelo.insperscore.partidas;

import com.pelo.insperscore.partidas.dto.CreatePartidaDTO;
import com.pelo.insperscore.partidas.dto.PartidaResponseDTO;
import com.pelo.insperscore.partidas.dto.UpdatePartidaDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/partidas")
@Tag(name = "Partidas", description = "Gerenciamento de partidas")
public class PartidasController {

    private final PartidasService partidasService;

    public PartidasController(PartidasService partidasService) {
        this.partidasService = partidasService;
    }

    @Operation(summary = "Listar partidas", description = "Retorna todas as partidas")
    @ApiResponse(responseCode = "200", description = "Lista retornada")
    @GetMapping
    public ResponseEntity<List<PartidaResponseDTO>> getAll() {
        return ResponseEntity.ok(partidasService.findAll());
    }

    @Operation(summary = "Obter partida por id", description = "Retorna uma partida pelo seu id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Partida encontrada"),
            @ApiResponse(responseCode = "404", description = "Partida não encontrada")
    })
    @GetMapping("/{id}")
    public ResponseEntity<PartidaResponseDTO> getById(@PathVariable Integer id) {
        PartidaResponseDTO dto = partidasService.findById(id);
        return ResponseEntity.ok(dto);
    }

    @Operation(summary = "Criar partida", description = "Cria uma nova partida")
    @ApiResponse(responseCode = "201", description = "Partida criada")
    @PostMapping
    public ResponseEntity<PartidaResponseDTO> create(@Valid @RequestBody CreatePartidaDTO dto) {
        PartidaResponseDTO created = partidasService.create(dto);
        return ResponseEntity.created(URI.create("/api/partidas/" + created.id())).body(created);
    }

    @Operation(summary = "Atualizar partida", description = "Atualiza os dados de uma partida")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Partida atualizada"),
            @ApiResponse(responseCode = "404", description = "Partida não encontrada")
    })
    @PutMapping("/{id}")
    public ResponseEntity<PartidaResponseDTO> update(@PathVariable Integer id,
                                                     @Valid @RequestBody UpdatePartidaDTO dto) {
        PartidaResponseDTO updated = partidasService.update(id, dto);
        return ResponseEntity.ok(updated);
    }

    @Operation(summary = "Deletar partida", description = "Remove uma partida pelo id")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Partida removida"),
            @ApiResponse(responseCode = "404", description = "Partida não encontrada")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        partidasService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
