package com.pelo.insperscore.estadios;

import com.pelo.insperscore.estadios.dto.CreateEstadioDTO;
import com.pelo.insperscore.estadios.dto.EstadioResponseDTO;
import com.pelo.insperscore.estadios.dto.UpdateEstadioDTO;
import com.pelo.insperscore.partidas.Partidas;
import com.pelo.insperscore.partidas.PartidasRepository;
import com.pelo.insperscore.times.Times;
import com.pelo.insperscore.times.TimesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EstadiosService {

    @Autowired
    private EstadiosRepository estadiosRepository;

    @Autowired
    private TimesRepository timesRepository;

    @Autowired
    private PartidasRepository partidasRepository;

    public List<EstadioResponseDTO> findAll() {
        return estadiosRepository.findAll()
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    public EstadioResponseDTO findById(Integer id) {
        Estadios e = estadiosRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Estádio não encontrado com id: " + id));
        return toResponse(e);
    }

    public EstadioResponseDTO create(CreateEstadioDTO dto) {
        Estadios e = new Estadios();
        e.setNome(dto.nome());

        if (dto.timeId() != null) {
            Times t = timesRepository.findById(dto.timeId())
                    .orElseThrow(() -> new RuntimeException("Time não encontrado com id: " + dto.timeId()));
            e.setTime(t);
        }

        Estadios saved = estadiosRepository.save(e);
        return toResponse(saved);
    }

    public EstadioResponseDTO update(Integer id, UpdateEstadioDTO dto) {
        Estadios existing = estadiosRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Estádio não encontrado com id: " + id));

        if (dto.nome() != null) {
            existing.setNome(dto.nome());
        }

        if (dto.timeId() != null) {
            Times t = timesRepository.findById(dto.timeId())
                    .orElseThrow(() -> new RuntimeException("Time não encontrado com id: " + dto.timeId()));
            existing.setTime(t);
        }

        if (dto.partidaIds() != null) {
            List<Partidas> partidas = partidasRepository.findAllById(dto.partidaIds());
            existing.setPartidas(partidas);
        }

        Estadios updated = estadiosRepository.save(existing);
        return toResponse(updated);
    }

    public void delete(Integer id) {
        if (!estadiosRepository.existsById(id)) {
            throw new RuntimeException("Estádio não encontrado com id: " + id);
        }
        estadiosRepository.deleteById(id);
    }

    private EstadioResponseDTO toResponse(Estadios e) {
        return new EstadioResponseDTO(e.getId(), e.getNome());
    }
}
