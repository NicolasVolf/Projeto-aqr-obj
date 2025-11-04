package com.pelo.insperscore.estadios;

import com.pelo.insperscore.estadios.dto.CreateEstadioDTO;
import com.pelo.insperscore.estadios.dto.EstadioResponseDTO;
import com.pelo.insperscore.estadios.dto.UpdateEstadioDTO;
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
@RequestMapping("/api/estadios")
@Tag(name = "Estádios", description = "Gerenciamento de estádios")
public class EstadiosController {

    private final EstadiosService estadiosService;

    public EstadiosController(EstadiosService estadiosService) {
        this.estadiosService = estadiosService;
    }

    @Operation(summary = "Listar estádios", description = "Retorna todos os estádios")
    @ApiResponse(responseCode = "200", description = "Lista retornada")
    @GetMapping
    public ResponseEntity<List<EstadioResponseDTO>> getAll() {
        return ResponseEntity.ok(estadiosService.findAll());
    }

    @Operation(summary = "Obter estádio por id", description = "Retorna um estádio pelo seu id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Estádio encontrado"),
            @ApiResponse(responseCode = "404", description = "Estádio não encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<EstadioResponseDTO> getById(@PathVariable Integer id) {
        EstadioResponseDTO dto = estadiosService.getPorId(id);
        return ResponseEntity.ok(dto);
    }

    @Operation(summary = "Criar estádio", description = "Cria um novo estádio")
    @ApiResponse(responseCode = "201", description = "Estádio criado")
    @PostMapping
    public ResponseEntity<EstadioResponseDTO> create(@Valid @RequestBody CreateEstadioDTO dto) {
        EstadioResponseDTO created = estadiosService.create(dto);
        return ResponseEntity.created(URI.create("/api/estadios/" + created.id())).body(created);
    }

    @Operation(summary = "Atualizar estádio", description = "Atualiza os dados de um estádio")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Estádio atualizado"),
            @ApiResponse(responseCode = "404", description = "Estádio não encontrado")
    })
    @PutMapping("/{id}")
    public ResponseEntity<EstadioResponseDTO> update(@PathVariable Integer id,
                                                     @Valid @RequestBody UpdateEstadioDTO dto) {
        EstadioResponseDTO updated = estadiosService.update(id, dto);
        return ResponseEntity.ok(updated);
    }

    @Operation(summary = "Deletar estádio", description = "Remove um estádio pelo id")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Estádio removido"),
            @ApiResponse(responseCode = "404", description = "Estádio não encontrado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        estadiosService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
