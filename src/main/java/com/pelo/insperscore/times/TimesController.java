package com.pelo.insperscore.times;

import com.pelo.insperscore.times.dto.CreateTimeDTO;
import com.pelo.insperscore.times.dto.TimeResponseDTO;
import com.pelo.insperscore.times.dto.UpdateTimeDTO;
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
@RequestMapping("/api/times")
@Tag(name = "Times", description = "Gerenciamento de times")
public class TimesController {

    private final TimesService timesService;

    public TimesController(TimesService timesService) {
        this.timesService = timesService;
    }

    @Operation(summary = "Listar times", description = "Retorna todos os times")
    @ApiResponse(responseCode = "200", description = "Lista retornada")
    @GetMapping
    public ResponseEntity<List<TimeResponseDTO>> getAll() {
        return ResponseEntity.ok(timesService.findAll());
    }

    @Operation(summary = "Obter time por id", description = "Retorna um time pelo seu id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Time encontrado"),
            @ApiResponse(responseCode = "404", description = "Time não encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<TimeResponseDTO> getById(@PathVariable Integer id) {
        TimeResponseDTO dto = timesService.getPorId(id);
        return ResponseEntity.ok(dto);
    }

    @Operation(summary = "Criar time", description = "Cria um novo time")
    @ApiResponse(responseCode = "201", description = "Time criado")
    @PostMapping
    public ResponseEntity<TimeResponseDTO> create(@Valid @RequestBody CreateTimeDTO dto) {
        TimeResponseDTO created = timesService.create(dto);
        return ResponseEntity.created(URI.create("/api/times/" + created.id())).body(created);
    }

    @Operation(summary = "Atualizar time", description = "Atualiza os dados de um time")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Time atualizado"),
            @ApiResponse(responseCode = "404", description = "Time não encontrado")
    })
    @PutMapping("/{id}")
    public ResponseEntity<TimeResponseDTO> update(@PathVariable Integer id,
                                                  @Valid @RequestBody UpdateTimeDTO dto) {
        TimeResponseDTO updated = timesService.update(id, dto);
        return ResponseEntity.ok(updated);
    }

    @Operation(summary = "Deletar time", description = "Remove um time pelo id")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Time removido"),
            @ApiResponse(responseCode = "404", description = "Time não encontrado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        timesService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
