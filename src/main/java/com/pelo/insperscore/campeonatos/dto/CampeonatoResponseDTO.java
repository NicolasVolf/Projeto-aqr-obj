package com.pelo.insperscore.campeonatos.dto;

import com.pelo.insperscore.campeonatos.Campeonatos;

public record CampeonatoResponseDTO(Integer id, String nome) {
    public CampeonatoResponseDTO toDto( Campeonatos campeonatos){

        return new CampeonatoResponseDTO(campeonatos.getId(), campeonatos.getNome());
    }

}

