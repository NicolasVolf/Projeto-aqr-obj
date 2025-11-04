package com.pelo.insperscore.estadios;

import com.pelo.insperscore.estadios.dto.CreateEstadioDTO;
import com.pelo.insperscore.estadios.dto.EstadioResponseDTO;
import com.pelo.insperscore.estadios.dto.UpdateEstadioDTO;
import com.pelo.insperscore.partidas.Partidas;
import com.pelo.insperscore.partidas.PartidasRepository;
import com.pelo.insperscore.times.Times;
import com.pelo.insperscore.times.TimesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

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
                .toList();
    }

    public Estadios buscarPorId(Integer id) {
        return estadiosRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public EstadioResponseDTO getPorId(Integer id) {
        Estadios e = buscarPorId(id);
        return toResponse(e);
    }

    public EstadioResponseDTO create(CreateEstadioDTO dto) {
        Estadios e = new Estadios();
        e.setNome(dto.nome());
        if (dto.timeId() != null) {
            Times t = timesRepository.findById(dto.timeId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
            e.setTime(t);
        }
        Estadios saved = estadiosRepository.save(e);
        return toResponse(saved);
    }

    public EstadioResponseDTO update(Integer id, UpdateEstadioDTO dto) {
        Estadios existing = buscarPorId(id);
        if (dto.nome() != null) existing.setNome(dto.nome());
        if (dto.timeId() != null) {
            Times t = timesRepository.findById(dto.timeId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
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
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        estadiosRepository.deleteById(id);
    }

    private EstadioResponseDTO toResponse(Estadios e) {
        return new EstadioResponseDTO(e.getId(), e.getNome());
    }
}
