package com.pelo.insperscore.jogadores;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JogadoresRepository extends JpaRepository<Jogadores, Integer> {
}

