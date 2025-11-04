package com.pelo.insperscore.partidas;

import com.pelo.insperscore.partidas.dto.CreatePartidaDTO;
import com.pelo.insperscore.partidas.dto.PartidaResponseDTO;
import com.pelo.insperscore.partidas.dto.UpdatePartidaDTO;
import com.pelo.insperscore.campeonatos.Campeonatos;
import com.pelo.insperscore.campeonatos.CampeonatosRepository;
import com.pelo.insperscore.estadios.Estadios;
import com.pelo.insperscore.estadios.EstadiosRepository;
import com.pelo.insperscore.jogadores.Jogadores;
import com.pelo.insperscore.jogadores.JogadoresRepository;
import com.pelo.insperscore.times.Times;
import com.pelo.insperscore.times.TimesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PartidasService {

    @Autowired
    private PartidasRepository partidasRepository;

    @Autowired
    private JogadoresRepository jogadoresRepository;

    @Autowired
    private CampeonatosRepository campeonatosRepository;

    @Autowired
    private EstadiosRepository estadiosRepository;

    @Autowired
    private TimesRepository timesRepository;

    public List<PartidaResponseDTO> findAll() {
        return partidasRepository.findAll()
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    public PartidaResponseDTO findById(Integer id) {
        Partidas p = partidasRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Partida não encontrada com id: " + id));
        return toResponse(p);
    }

    public PartidaResponseDTO create(CreatePartidaDTO dto) {
        Partidas p = new Partidas();
        p.setData(dto.data());
        p.setResultado(dto.resultado());

        if (dto.jogadorIds() != null && !dto.jogadorIds().isEmpty()) {
            List<Jogadores> jogadores = jogadoresRepository.findAllById(dto.jogadorIds());
            p.setJogadores(jogadores);
        }

        if (dto.campeonatoId() != null) {
            Campeonatos c = campeonatosRepository.findById(dto.campeonatoId())
                    .orElseThrow(() -> new RuntimeException("Campeonato não encontrado com id: " + dto.campeonatoId()));
            p.setCampeonato(c);
        }

        if (dto.estadioId() != null) {
            Estadios e = estadiosRepository.findById(dto.estadioId())
                    .orElseThrow(() -> new RuntimeException("Estádio não encontrado com id: " + dto.estadioId()));
            p.setEstadio(e);
        }

        if (dto.timeMandanteId() != null) {
            Times t = timesRepository.findById(dto.timeMandanteId())
                    .orElseThrow(() -> new RuntimeException("Time mandante não encontrado com id: " + dto.timeMandanteId()));
            p.setTimeMandante(t);
        }

        if (dto.timeVisitanteId() != null) {
            Times t2 = timesRepository.findById(dto.timeVisitanteId())
                    .orElseThrow(() -> new RuntimeException("Time visitante não encontrado com id: " + dto.timeVisitanteId()));
            p.setTimeVisitante(t2);
        }

        Partidas saved = partidasRepository.save(p);
        return toResponse(saved);
    }

    public PartidaResponseDTO update(Integer id, UpdatePartidaDTO dto) {
        Partidas existing = partidasRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Partida não encontrada com id: " + id));

        if (dto.data() != null) existing.setData(dto.data());
        if (dto.resultado() != null) existing.setResultado(dto.resultado());

        if (dto.jogadorIds() != null) {
            List<Jogadores> jogadores = jogadoresRepository.findAllById(dto.jogadorIds());
            existing.setJogadores(jogadores);
        }

        if (dto.campeonatoId() != null) {
            Campeonatos c = campeonatosRepository.findById(dto.campeonatoId())
                    .orElseThrow(() -> new RuntimeException("Campeonato não encontrado com id: " + dto.campeonatoId()));
            existing.setCampeonato(c);
        }

        if (dto.estadioId() != null) {
            Estadios e = estadiosRepository.findById(dto.estadioId())
                    .orElseThrow(() -> new RuntimeException("Estádio não encontrado com id: " + dto.estadioId()));
            existing.setEstadio(e);
        }

        if (dto.timeMandanteId() != null) {
            Times t = timesRepository.findById(dto.timeMandanteId())
                    .orElseThrow(() -> new RuntimeException("Time mandante não encontrado com id: " + dto.timeMandanteId()));
            existing.setTimeMandante(t);
        }

        if (dto.timeVisitanteId() != null) {
            Times t2 = timesRepository.findById(dto.timeVisitanteId())
                    .orElseThrow(() -> new RuntimeException("Time visitante não encontrado com id: " + dto.timeVisitanteId()));
            existing.setTimeVisitante(t2);
        }

        Partidas updated = partidasRepository.save(existing);
        return toResponse(updated);
    }

    public void delete(Integer id) {
        if (!partidasRepository.existsById(id)) {
            throw new RuntimeException("Partida não encontrada com id: " + id);
        }
        partidasRepository.deleteById(id);
    }

    private PartidaResponseDTO toResponse(Partidas p) {
        return PartidaResponseDTO.fromEntity(p);
    }
}
