package com.pelo.insperscore.partidas;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PartidasRepository extends JpaRepository<Partidas, Integer> {
}

