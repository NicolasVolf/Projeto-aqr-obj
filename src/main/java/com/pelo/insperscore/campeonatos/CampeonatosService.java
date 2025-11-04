package com.pelo.insperscore.campeonatos;

import com.pelo.insperscore.campeonatos.dto.CampeonatoResponseDTO;
import com.pelo.insperscore.campeonatos.dto.CreateCampeonatoDTO;
import com.pelo.insperscore.campeonatos.dto.UpdateCampeonatoDTO;
import com.pelo.insperscore.partidas.Partidas;
import com.pelo.insperscore.partidas.PartidasRepository;
import com.pelo.insperscore.times.Times;
import com.pelo.insperscore.times.TimesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CampeonatosService {

    @Autowired
    private CampeonatosRepository campeonatosRepository;

    @Autowired
    private TimesRepository timesRepository;

    @Autowired
    private PartidasRepository partidasRepository;

    public List<CampeonatoResponseDTO> findAll() {
        return campeonatosRepository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    public Campeonatos buscarPorId(Integer id) {
        return campeonatosRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public CampeonatoResponseDTO getPorId(Integer id) {
        Campeonatos c = buscarPorId(id);
        return toResponse(c);
    }

    public CampeonatoResponseDTO create(CreateCampeonatoDTO dto) {
        Campeonatos c = new Campeonatos();
        c.setNome(dto.nome());
        Campeonatos saved = campeonatosRepository.save(c);
        return toResponse(saved);
    }

    public CampeonatoResponseDTO update(Integer id, UpdateCampeonatoDTO dto) {
        Campeonatos existing = buscarPorId(id);
        if (dto.nome() != null) existing.setNome(dto.nome());
        if (dto.timeIds() != null) {
            List<Times> times = timesRepository.findAllById(dto.timeIds());
            existing.setTime(times);
        }
        if (dto.partidaIds() != null) {
            List<Partidas> partidas = partidasRepository.findAllById(dto.partidaIds());
            existing.setPartidas(partidas);
        }
        Campeonatos updated = campeonatosRepository.save(existing);
        return toResponse(updated);
    }

    public void delete(Integer id) {
        if (!campeonatosRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        campeonatosRepository.deleteById(id);
    }

    private CampeonatoResponseDTO toResponse(Campeonatos c) {
        return new CampeonatoResponseDTO(c.getId(), c.getNome());
    }
}
