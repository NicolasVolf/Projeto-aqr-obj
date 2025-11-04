package com.pelo.insperscore.jogadores;

import com.pelo.insperscore.jogadores.dto.CreateJogadorDTO;
import com.pelo.insperscore.jogadores.dto.JogadorResponseDTO;
import com.pelo.insperscore.jogadores.dto.UpdateJogadorDTO;
import com.pelo.insperscore.times.Times;
import com.pelo.insperscore.times.TimesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class JogadoresService {

    @Autowired
    private JogadoresRepository jogadoresRepository;

    @Autowired
    private TimesRepository timesRepository;

    public List<JogadorResponseDTO> findAll() {
        return jogadoresRepository.findAll()
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    public JogadorResponseDTO findById(Integer id) {
        Jogadores j = jogadoresRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Jogador não encontrado com id: " + id));
        return toResponse(j);
    }

    public JogadorResponseDTO create(CreateJogadorDTO dto) {
        Jogadores j = new Jogadores();
        j.setNome(dto.nome());
        j.setPosicao(dto.posicao());
        j.setNumero(dto.numero());
        j.setTitulos(dto.titulos());
        j.setIdade(dto.idade());
        j.setNacionalidade(dto.nacionalidade());

        if (dto.timeId() != null) {
            Times t = timesRepository.findById(dto.timeId())
                    .orElseThrow(() -> new RuntimeException("Time não encontrado com id: " + dto.timeId()));
            j.setTime(t);
        }

        Jogadores saved = jogadoresRepository.save(j);
        return toResponse(saved);
    }

    public JogadorResponseDTO update(Integer id, UpdateJogadorDTO dto) {
        Jogadores existing = jogadoresRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Jogador não encontrado com id: " + id));

        if (dto.nome() != null) existing.setNome(dto.nome());
        if (dto.posicao() != null) existing.setPosicao(dto.posicao());
        if (dto.numero() != null) existing.setNumero(dto.numero());
        if (dto.titulos() != null) existing.setTitulos(dto.titulos());
        if (dto.idade() != null) existing.setIdade(dto.idade());
        if (dto.nacionalidade() != null) existing.setNacionalidade(dto.nacionalidade());

        if (dto.timeId() != null) {
            Times t = timesRepository.findById(dto.timeId())
                    .orElseThrow(() -> new RuntimeException("Time não encontrado com id: " + dto.timeId()));
            existing.setTime(t);
        }

        Jogadores updated = jogadoresRepository.save(existing);
        return toResponse(updated);
    }

    public void delete(Integer id) {
        if (!jogadoresRepository.existsById(id)) {
            throw new RuntimeException("Jogador não encontrado com id: " + id);
        }
        jogadoresRepository.deleteById(id);
    }

    private JogadorResponseDTO toResponse(Jogadores j) {
        // monta JogadorResponseDTO conforme o record que você enviou
        return new JogadorResponseDTO(
                j.getId(),
                j.getNome(),
                j.getPosicao(),
                j.getNumero(),
                j.getIdade(),
                j.getNacionalidade()
        );
    }
}
