package com.pelo.insperscore.times;

import com.pelo.insperscore.times.dto.CreateTimeDTO;
import com.pelo.insperscore.times.dto.TimeResponseDTO;
import com.pelo.insperscore.times.dto.UpdateTimeDTO;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/times")
public class TimesController {

    private final TimesService timesService;

    public TimesController(TimesService timesService) {
        this.timesService = timesService;
    }

    @GetMapping
    public ResponseEntity<List<TimeResponseDTO>> getAll() {
        return ResponseEntity.ok(timesService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TimeResponseDTO> getById(@PathVariable Integer id) {
        TimeResponseDTO dto = timesService.getPorId(id);
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<TimeResponseDTO> create(@Valid @RequestBody CreateTimeDTO dto) {
        TimeResponseDTO created = timesService.create(dto);
        return ResponseEntity.created(URI.create("/api/times/" + created.id())).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TimeResponseDTO> update(@PathVariable Integer id,
                                                  @Valid @RequestBody UpdateTimeDTO dto) {
        TimeResponseDTO updated = timesService.update(id, dto);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        timesService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
