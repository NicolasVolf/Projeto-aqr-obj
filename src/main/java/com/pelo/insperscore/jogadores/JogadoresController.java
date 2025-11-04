package com.pelo.insperscore.jogadores;

import com.pelo.insperscore.jogadores.dto.CreateJogadorDTO;
import com.pelo.insperscore.jogadores.dto.JogadorResponseDTO;
import com.pelo.insperscore.jogadores.dto.UpdateJogadorDTO;
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
@RequestMapping("/api/jogadores")
@Tag(name = "Jogadores", description = "Gerenciamento de jogadores")
public class JogadoresController {

    private final JogadoresService jogadoresService;

    public JogadoresController(JogadoresService jogadoresService) {
        this.jogadoresService = jogadoresService;
    }

    @Operation(summary = "Listar jogadores", description = "Retorna todos os jogadores")
    @ApiResponse(responseCode = "200", description = "Lista retornada")
    @GetMapping
    public ResponseEntity<List<JogadorResponseDTO>> getAll() {
        return ResponseEntity.ok(jogadoresService.findAll());
    }

    @Operation(summary = "Obter jogador por id", description = "Retorna um jogador pelo seu id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Jogador encontrado"),
            @ApiResponse(responseCode = "404", description = "Jogador não encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<JogadorResponseDTO> getById(@PathVariable Integer id) {
        JogadorResponseDTO dto = jogadoresService.getPorId(id);
        return ResponseEntity.ok(dto);
    }

    @Operation(summary = "Criar jogador", description = "Cria um novo jogador")
    @ApiResponse(responseCode = "201", description = "Jogador criado")
    @PostMapping
    public ResponseEntity<JogadorResponseDTO> create(@Valid @RequestBody CreateJogadorDTO dto) {
        JogadorResponseDTO created = jogadoresService.create(dto);
        return ResponseEntity.created(URI.create("/api/jogadores/" + created.id())).body(created);
    }

    @Operation(summary = "Atualizar jogador", description = "Atualiza os dados de um jogador")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Jogador atualizado"),
            @ApiResponse(responseCode = "404", description = "Jogador não encontrado")
    })
    @PutMapping("/{id}")
    public ResponseEntity<JogadorResponseDTO> update(@PathVariable Integer id,
                                                     @Valid @RequestBody UpdateJogadorDTO dto) {
        JogadorResponseDTO updated = jogadoresService.update(id, dto);
        return ResponseEntity.ok(updated);
    }

    @Operation(summary = "Deletar jogador", description = "Remove um jogador pelo id")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Jogador removido"),
            @ApiResponse(responseCode = "404", description = "Jogador não encontrado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        jogadoresService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
