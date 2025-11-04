package com.pelo.insperscore.times;

import com.pelo.insperscore.campeonatos.Campeonatos;
import com.pelo.insperscore.campeonatos.CampeonatosRepository;
import com.pelo.insperscore.jogadores.Jogadores;
import com.pelo.insperscore.jogadores.JogadoresRepository;
import com.pelo.insperscore.times.dto.CreateTimeDTO;
import com.pelo.insperscore.times.dto.TimeResponseDTO;
import com.pelo.insperscore.times.dto.UpdateTimeDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TimesService {

    @Autowired
    private TimesRepository timesRepository;

    @Autowired
    private CampeonatosRepository campeonatosRepository;

    @Autowired
    private JogadoresRepository jogadoresRepository;

    public List<TimeResponseDTO> findAll() {
        return timesRepository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    public Times buscarPorId(Integer id) {
        return timesRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public TimeResponseDTO getPorId(Integer id) {
        Times t = buscarPorId(id);
        return toResponse(t);
    }

    public TimeResponseDTO create(CreateTimeDTO dto) {
        Times t = new Times();
        t.setNome(dto.nome());
        t.setTitulos(dto.titulos());
        Times saved = timesRepository.save(t);
        return toResponse(saved);
    }

    public TimeResponseDTO update(Integer id, UpdateTimeDTO dto) {
        Times existing = buscarPorId(id);
        if (dto.nome() != null) existing.setNome(dto.nome());
        if (dto.titulos() != null) existing.setTitulos(dto.titulos());
        if (dto.campeonatoIds() != null) {
            List<Campeonatos> campeonatos = campeonatosRepository.findAllById(dto.campeonatoIds());
            existing.setCampeonatos(campeonatos);
        }
        if (dto.jogadorIds() != null) {
            List<Jogadores> jogadores = jogadoresRepository.findAllById(dto.jogadorIds());
            existing.setJogadores(jogadores);
        }
        Times updated = timesRepository.save(existing);
        return toResponse(updated);
    }

    public void delete(Integer id) {
        if (!timesRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        timesRepository.deleteById(id);
    }

    private TimeResponseDTO toResponse(Times t) {
        return new TimeResponseDTO(t.getId(), t.getNome());
    }
}
