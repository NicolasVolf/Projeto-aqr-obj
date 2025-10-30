package com.pelo.insperscore.campeonatos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CampeonatosRepository extends JpaRepository<Campeonatos, Integer> {
}

