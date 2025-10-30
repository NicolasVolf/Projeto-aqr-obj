package com.pelo.insperscore.estadios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstadiosRepository extends JpaRepository<Estadios, Integer> {
}

