package com.pelo.insperscore.estadios.dto;

import com.pelo.insperscore.estadios.Estadios;

public record EstadioResponseDTO(Integer id, String nome) {
    public EstadioResponseDTO toDto(Estadios estadios) {
        return new EstadioResponseDTO(estadios.getId(), estadios.getNome());
    }
}

