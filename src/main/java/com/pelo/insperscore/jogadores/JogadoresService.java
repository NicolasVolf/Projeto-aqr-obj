package com.pelo.insperscore.jogadores;

import com.pelo.insperscore.jogadores.dto.CreateJogadorDTO;
import com.pelo.insperscore.jogadores.dto.JogadorResponseDTO;
import com.pelo.insperscore.jogadores.dto.UpdateJogadorDTO;
import com.pelo.insperscore.times.Times;
import com.pelo.insperscore.times.TimesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

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
                .toList();
    }

    public Jogadores buscarPorId(Integer id) {
        return jogadoresRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public JogadorResponseDTO getPorId(Integer id) {
        Jogadores j = buscarPorId(id);
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
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
            j.setTime(t);
        }
        Jogadores saved = jogadoresRepository.save(j);
        return toResponse(saved);
    }

    public JogadorResponseDTO update(Integer id, UpdateJogadorDTO dto) {
        Jogadores existing = buscarPorId(id);
        if (dto.nome() != null) existing.setNome(dto.nome());
        if (dto.posicao() != null) existing.setPosicao(dto.posicao());
        if (dto.numero() != null) existing.setNumero(dto.numero());
        if (dto.titulos() != null) existing.setTitulos(dto.titulos());
        if (dto.idade() != null) existing.setIdade(dto.idade());
        if (dto.nacionalidade() != null) existing.setNacionalidade(dto.nacionalidade());
        if (dto.timeId() != null) {
            Times t = timesRepository.findById(dto.timeId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
            existing.setTime(t);
        }
        Jogadores updated = jogadoresRepository.save(existing);
        return toResponse(updated);
    }

    public void delete(Integer id) {
        if (!jogadoresRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        jogadoresRepository.deleteById(id);
    }

    private JogadorResponseDTO toResponse(Jogadores j) {
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
