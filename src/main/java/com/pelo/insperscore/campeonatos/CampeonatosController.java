package com.pelo.insperscore.campeonatos;

import com.pelo.insperscore.campeonatos.dto.CampeonatoResponseDTO;
import com.pelo.insperscore.campeonatos.dto.CreateCampeonatoDTO;
import com.pelo.insperscore.campeonatos.dto.UpdateCampeonatoDTO;
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
@RequestMapping("/api/campeonatos")
@Tag(name = "Campeonatos", description = "Gerenciamento de campeonatos")
public class CampeonatosController {

    private final CampeonatosService campeonatosService;

    public CampeonatosController(CampeonatosService campeonatosService) {
        this.campeonatosService = campeonatosService;
    }

    @Operation(summary = "Listar campeonatos", description = "Retorna todos os campeonatos")
    @ApiResponse(responseCode = "200", description = "Lista retornada")
    @GetMapping
    public ResponseEntity<List<CampeonatoResponseDTO>> getAll() {
        return ResponseEntity.ok(campeonatosService.findAll());
    }

    @Operation(summary = "Obter campeonato por id", description = "Retorna um campeonato pelo seu id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Campeonato encontrado"),
            @ApiResponse(responseCode = "404", description = "Campeonato não encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<CampeonatoResponseDTO> getById(@PathVariable Integer id) {
        CampeonatoResponseDTO dto = campeonatosService.getPorId(id);
        return ResponseEntity.ok(dto);
    }

    @Operation(summary = "Criar campeonato", description = "Cria um novo campeonato")
    @ApiResponse(responseCode = "201", description = "Campeonato criado")
    @PostMapping
    public ResponseEntity<CampeonatoResponseDTO> create(@Valid @RequestBody CreateCampeonatoDTO dto) {
        CampeonatoResponseDTO created = campeonatosService.create(dto);
        return ResponseEntity.created(URI.create("/api/campeonatos/" + created.id())).body(created);
    }

    @Operation(summary = "Atualizar campeonato", description = "Atualiza os dados de um campeonato")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Campeonato atualizado"),
            @ApiResponse(responseCode = "404", description = "Campeonato não encontrado")
    })
    @PutMapping("/{id}")
    public ResponseEntity<CampeonatoResponseDTO> update(@PathVariable Integer id,
                                                        @Valid @RequestBody UpdateCampeonatoDTO dto) {
        CampeonatoResponseDTO updated = campeonatosService.update(id, dto);
        return ResponseEntity.ok(updated);
    }

    @Operation(summary = "Deletar campeonato", description = "Remove um campeonato pelo id")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Campeonato removido"),
            @ApiResponse(responseCode = "404", description = "Campeonato não encontrado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        campeonatosService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
