package com.pelo.insperscore.campeonatos;

import com.pelo.insperscore.campeonatos.dto.CampeonatoResponseDTO;
import com.pelo.insperscore.campeonatos.dto.CreateCampeonatoDTO;
import com.pelo.insperscore.campeonatos.dto.UpdateCampeonatoDTO;
import com.pelo.insperscore.partidas.Partidas;
import com.pelo.insperscore.partidas.PartidasRepository;
import com.pelo.insperscore.times.Times;
import com.pelo.insperscore.times.TimesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public List<com.pelo.insperscore.campeonatos.dto.CampeonatoResponseDTO> findAll() {
        return campeonatosRepository.findAll()
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    public com.pelo.insperscore.campeonatos.dto.CampeonatoResponseDTO findById(Integer id) {
        Campeonatos c = campeonatosRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Campeonato não encontrado com id: " + id));
        return toResponse(c);
    }

    public com.pelo.insperscore.campeonatos.dto.CampeonatoResponseDTO create(CreateCampeonatoDTO dto) {
        Campeonatos c = new Campeonatos();
        c.setNome(dto.nome());
        Campeonatos saved = campeonatosRepository.save(c);
        return toResponse(saved);
    }

    public com.pelo.insperscore.campeonatos.dto.CampeonatoResponseDTO update(Integer id, UpdateCampeonatoDTO dto) {
        Campeonatos existing = campeonatosRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Campeonato não encontrado com id: " + id));

        if (dto.nome() != null) {
            existing.setNome(dto.nome());
        }

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
            throw new RuntimeException("Campeonato não encontrado com id: " + id);
        }
        campeonatosRepository.deleteById(id);
    }

    private com.pelo.insperscore.campeonatos.dto.CampeonatoResponseDTO toResponse(Campeonatos c) {
        return new com.pelo.insperscore.campeonatos.dto.CampeonatoResponseDTO(c.getId(), c.getNome());
    }
}
