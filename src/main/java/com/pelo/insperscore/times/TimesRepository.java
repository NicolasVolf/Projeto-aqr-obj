package com.pelo.insperscore.times;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TimesRepository extends JpaRepository<Times, Integer> {
}
