package com.pelo.insperscore.times.dto;

import com.pelo.insperscore.times.Times;

public record TimeResponseDTO(Integer id, String nome) {
    public TimeResponseDTO toDto(Times times) {
        return new TimeResponseDTO(times.getId(), times.getNome());
    }
}

